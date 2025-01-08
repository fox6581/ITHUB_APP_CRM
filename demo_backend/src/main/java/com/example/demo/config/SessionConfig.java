package com.example.demo.config;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionConfig {

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                System.out.println("Session created: " + se.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {
                System.out.println("Session destroyed: " + se.getSession().getId());
            }
        };
    }
}
