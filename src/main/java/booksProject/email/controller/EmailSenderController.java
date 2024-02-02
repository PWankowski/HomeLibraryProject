package booksProject.email.controller;

import booksProject.email.EmailSendingException;
import booksProject.email.service.EmailService;
import booksProject.user.NoUserFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail/")
@RequiredArgsConstructor
@Slf4j
public class EmailSenderController {

    private final EmailService emailService;

    @PostMapping("sendEmail")
    public ResponseEntity sendEmail(@RequestParam String login){

        emailService.sendSimpleMailMessage(login);
        return  ResponseEntity.ok("Email sent");
    }

    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity handleNoUserFoundException(NoUserFoundException exception) {

        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EmailSendingException.class)
    public ResponseEntity handleEmailSendingException(EmailSendingException exception) {

        log.error(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
