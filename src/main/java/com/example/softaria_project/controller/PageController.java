package com.example.softaria_project.controller;

import com.example.softaria_project.service.PageService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

/**
 * Контроллер для обработки запросов к корневому URL ("/").
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/pages")
public class PageController {
    private final PageService pageService;

    /**
     * Обрабатывает GET-запросы к корневому URL ("/").
     *
     * @return Имя шаблона главной страницы.
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Обрабатывает POST-запросы для добавления URL данных за вчерашний день.
     *
     * @param url   URL, который нужно добавить.
     * @param model Объект Model для добавления атрибутов.
     * @return Имя шаблона главной страницы.
     */
    @PostMapping("/past-url")
    public String pastUrl(@RequestParam @NotBlank @URL String url, Model model) {
        pageService.addPastData(URI.create(url));
        model.addAttribute("pastUrls", pageService.getPastData());
        model.addAttribute("presentUrls", pageService.getPresentData());
        return "home";
    }

    /**
     * Обрабатывает POST-запросы для добавления URL данных за сегодняшний день.
     *
     * @param url   URL, который нужно добавить.
     * @param model Объект Model для добавления атрибутов.
     * @return Имя шаблона главной страницы.
     */
    @PostMapping("/present-url")
    public String presentUrl(@RequestParam @NotBlank @URL String url, Model model) {
        pageService.addPresentData(URI.create(url));
        model.addAttribute("pastUrls", pageService.getPastData());
        model.addAttribute("presentUrls", pageService.getPresentData());
        return "home";
    }

    /**
     * Обрабатывает POST-запросы для отправки электронной почты.
     *
     * @param email Электронная почта.
     * @param model Объект Model для добавления атрибутов.
     * @return Имя шаблона главной страницы.
     */
    @PostMapping("/send")
    public String sendEmail(@RequestParam @Email String email, Model model) {
        pageService.sendEmail(email);
        model.addAttribute("pastUrls", pageService.getPastData());
        model.addAttribute("presentUrls", pageService.getPresentData());
        return "home";
    }

    /**
     * Обрабатывает POST-запросы для удаления URL данных за вчерашний день.
     *
     * @param url   URL, который нужно удалить.
     * @param model Объект Model для добавления атрибутов.
     * @return Имя шаблона главной страницы.
     */
    @PostMapping("/delete-past-url")
    public String deletePastUrl(@RequestParam @NotBlank @URL String url, Model model) {
        pageService.removePastData(URI.create(url));
        model.addAttribute("pastUrls", pageService.getPastData());
        model.addAttribute("presentUrls", pageService.getPresentData());
        return "home";
    }

    /**
     * Обрабатывает POST-запросы для удаления URL данных за сегодняшний день.
     *
     * @param url   URL, который нужно удалить.
     * @param model Объект Model для добавления атрибутов.
     * @return Имя шаблона главной страницы.
     */
    @PostMapping("/delete-present-url")
    public String deletePresentUrl(@RequestParam @NotBlank @URL String url, Model model) {
        pageService.removePresentData(URI.create(url));
        model.addAttribute("pastUrls", pageService.getPastData());
        model.addAttribute("presentUrls", pageService.getPresentData());
        return "home";
    }
}
