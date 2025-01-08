package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Глобальный обработчик исключений для всего приложения.
 * Аннотация @ControllerAdvice позволяет перехватывать и обрабатывать исключения,
 * которые возникают в контроллерах.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработчик исключения UserAlreadyExistsException.
     * Когда данное исключение выбрасывается, этот метод будет вызван для обработки.
     *
     * @param ex Исключение, которое выбрасывается, когда пользователь с данным email уже существует.
     * @return ResponseEntity с сообщением об ошибке и статусом HTTP 409 (CONFLICT).
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        // Возвращает сообщение об ошибке и HTTP статус 409 (Conflict).
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
