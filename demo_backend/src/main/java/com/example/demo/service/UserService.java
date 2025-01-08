package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users saveUser(Users user) {
        // Шифруем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Users authenticateUser(String email, String rawPassword) {
        Users user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null; // В случае неуспешной аутентификации
    }

    public void registerUser(String name, String email, String password, String phone, Role role) {
        Users newUser = new Users(name, email, password, phone, role);
        saveUser(newUser);
        // Сохраните пользователя в базе данных
    }


}