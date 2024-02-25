package booksProject.email.service;

public interface EmailService {

    void sendSimpleMailMessage(String login);

    void sendHtmlMailMessage(String login);
}
