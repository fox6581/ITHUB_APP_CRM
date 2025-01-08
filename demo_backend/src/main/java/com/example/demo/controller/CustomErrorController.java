package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        // Логика обработки ошибки
        return "Произошла ошибка: " + e.getMessage(); // Или логируйте это сообщение
    }

    // Здесь можно добавить другие методы для обработки конкретных исключений
}
