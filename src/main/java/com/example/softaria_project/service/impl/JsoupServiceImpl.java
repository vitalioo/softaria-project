package com.example.softaria_project.service.impl;

import com.example.softaria_project.exception.JsoupException;
import com.example.softaria_project.service.JsoupService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

/**
 * Реализация интерфейса JsoupService, которая получает HTML-контент с заданного URI.
 * Этот класс использует библиотеку Jsoup для подключения к URI и получения HTML-кода.
 * Логи используются для записи процесса и обработки исключений.
 */
@Service
@Slf4j
public class JsoupServiceImpl implements JsoupService {

    /**
     * Получает HTML-код по указанному URI.
     *
     * @param uri URI, откуда нужно получить HTML-контент.
     * @return HTML-контент в виде строки.
     * @throws JsoupException если произошла ошибка при получении HTML-контента.
     */
    @Override
    public String getHtmlCodeFromURI(URI uri) {
        try {
            Document document = Jsoup.connect(uri.toString()).timeout(10000).get();
            log.debug("Get html code from URI: {}", uri);
            return document.html();
        } catch (IOException e) {
            log.error("Error while getting html code from URI: {}", uri);
            throw new JsoupException("Error while getting html code from URI: " + uri);
        }
    }
}
