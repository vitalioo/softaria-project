# Автоматизированная система мониторинга
![project-logo.png](assets%2Fproject-logo.png)

Добро пожаловать в репозиторий автоматизированной системы мониторинга! Это проект системы, позволяющий удобный способ управления страницами
##Технологии
- Java 17
- Spring Boot 3
- Spring Mail
- Thymeleaf

## Установка и настройка
1. Убедитесь, что у вас установлены Java 20 и Apache Maven;
2. Клонируйте репозиторий на свою локальную машину;
3. Настройте переменные среды:
   + SPRING_SERVER_PORT - порт приложения
   + SOFTARIA_MAIL_HOST - хост SMTP сервера
   + SOFTARIA_MAIL_PORT - порт SMTP сервера
   + SOFTARIA_MAIL_USERNAME - имя пользователя SMTP сервера
   + SOFTARIA_MAIL_PASSWORD - пароль пользователя SMTP сервера (в некоторых случаях может понадобиться настроить двухфакторную аутентификацию и создать пароль для приложения на локальной машине)
