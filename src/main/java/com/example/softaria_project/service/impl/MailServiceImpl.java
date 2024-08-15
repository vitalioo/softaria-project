package com.example.softaria_project.service.impl;

import com.example.softaria_project.exception.MailException;
import com.example.softaria_project.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса MailService, которая отправляет электронные письма с использованием JavaMailSender.
 * Этот класс отвечает за создание и отправку MIME-сообщений.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * Отправляет письмо с указанным содержимым на заданный адрес электронной почты.
     *
     * @param email адрес электронной почты получателя.
     * @param text  содержимое письма.
     * @throws MailException если произошла ошибка при отправке письма.
     */
    @Override
    public void sendLetter(String email, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("Изменения в URL");
            helper.setText(text);

            mailSender.send(message);
            log.info("Email sent to: {}", email);
        } catch (MessagingException e) {
            log.error("Error sending email", e);
            throw new MailException("Error sending email: {}" + e);
        }
    }
}
