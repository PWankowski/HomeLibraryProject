package booksProject.email.service;

import booksProject.books.entity.BookEntity;
import booksProject.books.entity.BookTagEntity;
import booksProject.email.EmailSendingException;
import booksProject.shelves.entity.BookShelf;
import booksProject.user.NoUserFoundException;
import booksProject.user.entity.UserEntity;
import booksProject.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    public static final String SHELVES_WITH_BOOKS = "Your books shelves that you want to read in the future";
    private String host;
    private String fromEmail;
    private final JavaMailSender emailSender;
    private final UserRepository userRepository;

    @Autowired
    public EmailServiceImpl(@Value("${spring.mail.verify.host}") String host, @Value("${spring.mail.username}") String fromEmail, JavaMailSender emailSender, UserRepository userRepository) {
        this.host = host;
        this.fromEmail = fromEmail;
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }

    @Override
    public void sendSimpleMailMessage(String login) throws EmailSendingException{

        UserEntity user = userRepository.findByLogin(login).orElseThrow( () -> new NoUserFoundException(login));
        String content = createContentMessage(user);
        String receiverEmail = user.getEmailAddress();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(SHELVES_WITH_BOOKS);
            message.setFrom(fromEmail);
            message.setTo(receiverEmail);
            message.setText(content);
            emailSender.send(message);
        } catch (Exception exception) {
            log.error(exception.getLocalizedMessage());
            throw new EmailSendingException("Error while sending email! : " + exception.getLocalizedMessage());
        }
    }

    private String createContentMessage(UserEntity user) {

        Set<BookShelf> shelves = user.getShelves();
        StringBuilder books = new StringBuilder();
        for(BookShelf shelf : shelves) {
            books.append("BookShelf name : " + shelf.getName() + System.getProperty("line.separator"));
            List<BookEntity> booksFromShelf = shelf.getItems();
            if(!booksFromShelf.isEmpty()) {
                for(BookEntity entity : booksFromShelf) {
                    books.append("Book Title : " + entity.getTitle()+ System.getProperty("line.separator"));
                    books.append("Author : " + entity.getAuthor()+ System.getProperty("line.separator"));
                    books.append("Publisher : " + entity.getDetails().getPublisher() + System.getProperty("line.separator"));
                    books.append("ISBN : " + entity.getDetails().getIsbn() + System.getProperty("line.separator"));
                    books.append("Language : " + entity.getDetails().getLang() + System.getProperty("line.separator"));
                    books.append("Description : " + entity.getDetails().getDescription() + System.getProperty("line.separator"));
                    Set<BookTagEntity> tags = entity.getTags();

                    if(!tags.isEmpty()) {
                        for (BookTagEntity bookTag : tags) {
                            books.append("Tag : " + bookTag.getTagValue() + System.getProperty("line.separator"));
                        }
                    }
                    books.append("-------------------------------" + System.getProperty("line.separator"));
                }
            }
            books.append(System.getProperty("line.separator"));
        }
        return books.toString();
    }
}
