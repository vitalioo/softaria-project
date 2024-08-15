package com.example.softaria_project.service.impl;

import com.example.softaria_project.service.JsoupService;
import com.example.softaria_project.service.MailService;
import com.example.softaria_project.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Реализация интерфейса PageService для мониторинга данных веб-страниц.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PageServiceImpl implements PageService {
    private final Map<URI, String> pastData = new HashMap<>();

    private final Map<URI, String> presentData = new HashMap<>();

    private final JsoupService jsoupService;

    private final MailService mailService;

    /**
     * Добавляет данные о прошлой версии страницы по-указанному URI.
     *
     * @param pastUri URI страницы
     */
    @Override
    public void addPastData(URI pastUri) {
        String htmlCode = jsoupService.getHtmlCodeFromURI(pastUri);
        pastData.put(pastUri, htmlCode);
        log.debug("Add past data: {}", pastUri);
    }

    /**
     * Добавляет данные о текущей версии страницы по-указанному URI.
     *
     * @param presentUri URI страницы
     */
    @Override
    public void addPresentData(URI presentUri) {
        String htmlCode = jsoupService.getHtmlCodeFromURI(presentUri);
        presentData.put(presentUri, htmlCode);
        log.debug("Add present data: {}", presentUri);
    }

    /**
     * Возвращает набор URI прошлых данных.
     *
     * @return Набор URI прошлых данных
     */
    @Override
    public Set<URI> getPastData() {
        return pastData.keySet();
    }

    /**
     * Возвращает набор URI текущих данных.
     *
     * @return Набор URI текущих данных
     */
    @Override
    public Set<URI> getPresentData() {
        return presentData.keySet();
    }

    /**
     * Отправляет email с отчетом по изменениям в данных.
     *
     * @param email Адрес электронной почты получателя
     */
    @Override
    public void sendEmail(String email) {
        mailService.sendLetter(email, createLetter(email));
    }

    /**
     * Удаляет данные о прошлой версии страницы по-указанному URI.
     *
     * @param uri URI страницы
     */
    @Override
    public void removePastData(URI uri) {
        pastData.remove(uri);
        log.debug("Remove past data: {}", uri);
    }

    /**
     * Удаляет данные о текущей версии страницы по-указанному URI.
     *
     * @param uri URI страницы
     */
    @Override
    public void removePresentData(URI uri) {
        presentData.remove(uri);
        log.debug("Remove present data: {}", uri);
    }

    /**
     * Создает текст письма для отправки на указанный email.
     *
     * @param email Адрес электронной почты получателя
     * @return Текст письма
     */
    private String createLetter(String email) {
        return String.format("Здравствуйте, дорогая(ой) %s %n%n", email.substring(0, email.indexOf("@"))) +
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n" +
                "Исчезли следующие страницы: " + getRemovedPages() + "\n" +
                "Появились следующие новые страницы: " + getNewPages() + "\n" +
                "Изменились следующие страницы: " + getChangedPages() + "\n\n" +
                """
                        С уважением,
                        автоматизированная система
                        мониторинга.""";
    }

    /**
     * Возвращает список URI удаленных страниц.
     *
     * @return Список URI удаленных страниц
     */
    private List<URI> getRemovedPages() {
        return pastData.keySet().stream().filter(uri -> !presentData.containsKey(uri)).toList();
    }

    /**
     * Возвращает список URI новых страниц.
     *
     * @return Список URI новых страниц
     */
    private List<URI> getNewPages() {
        return presentData.keySet().stream().filter(uri -> !pastData.containsKey(uri)).toList();
    }

    /**
     * Возвращает список URI измененных страниц.
     *
     * @return Список URI измененных страниц
     */
    private List<URI> getChangedPages() {
        return presentData.keySet().stream().filter(uri -> pastData.containsKey(uri) && !pastData.get(uri).equals(presentData.get(uri))).toList();
    }

}
