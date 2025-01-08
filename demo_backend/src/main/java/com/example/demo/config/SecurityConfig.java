package com.example.demo.config;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration

public class SecurityConfig implements WebMvcConfigurer {


    // Убираем прямое инжектирование UserService в SecurityConfig
    // CORS Configuration - Configure global CORS settings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3001") // разрешаем только фронтенд на порту 5500
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);  // если нужно поддерживать cookies
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        // Инжектируем UserService через параметр метода
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                // Получаем пользователя из базы данных по email
                Users user = userService.getUserByEmail(email);
                if (user == null) {
                    throw new UsernameNotFoundException("Пользователь с email " + email + " не найден");
                }

                // Возвращаем UserDetails с ролью, соответствующей пользователю
                return User.withUsername(user.getEmail())
                        .password(user.getPassword())  // Не нужно снова кодировать пароль, если он уже закодирован
                        .roles(user.getRole().name())  // Преобразуем роль в строку
                        .build();
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/clients/**", "/clients").permitAll()
                        .requestMatchers("/services/**", "/services").permitAll()
                        .requestMatchers("/masters/**", "/masters").permitAll()
                        .requestMatchers("/workhours/**", "/workhours").permitAll()
                        .requestMatchers("/months/**", "/months").permitAll()
                        .requestMatchers("/appointments/**", "/appointments").permitAll()
                        .requestMatchers("/appointments/block/**").permitAll()
                        .requestMatchers("/appointments/update/**").permitAll()
                        .requestMatchers("/slots/**", "/slots").permitAll()
                        .requestMatchers("/master-qualification-services/**", "/master-qualification-services").permitAll()
                        .requestMatchers("/master-qualifications/**", "/master-qualifications").permitAll()
                        .requestMatchers("/qualifications/**", "/qualifications").permitAll()
                        .requestMatchers("/users", "/users/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/dashboard", "/dashboard/").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())  // Используем `withDefaults()` для HTTP Basic
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
  // Перенаправляем на страницу логина, если сессия истекла
        ;
        return http.build();
    }



}
