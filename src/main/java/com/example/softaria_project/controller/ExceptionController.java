package com.example.softaria_project.controller;

import com.example.softaria_project.exception.JsoupException;
import com.example.softaria_project.exception.MailException;
import com.example.softaria_project.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Контроллер для обработки исключений.
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Обработчик исключений типа JsoupException.
     *
     * @param e Исключение JsoupException.
     * @return Ответ с сообщением об ошибке и статусом OK.
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(JsoupException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Обработчик исключений типа MailException.
     *
     * @param e Исключение MailException.
     * @return Ответ с сообщением об ошибке и статусом OK.
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(MailException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
