package com.example.softaria_project.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс модели ответа для обработки исключений.
 */
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    /**
     * Сообщение об ошибке.
     */
    private String message;
}
