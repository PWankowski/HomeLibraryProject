package booksProject.googlebooks.controller;

import booksProject.books.NoBookFoundException;
import booksProject.googlebooks.service.GoogleBooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@Slf4j
@RestController("/googleBooks")
public class GoogleBooksController {
    
    private final GoogleBooksService googleBooksService;

    @Autowired
    public GoogleBooksController(GoogleBooksService googleBooksService) {
        this.googleBooksService = googleBooksService;
    }

    @GetMapping("/book/{idBook}")
    public ResponseEntity getBook(@PathVariable String idBook) {

        try {
            return ResponseEntity.ok(googleBooksService.getBook(idBook));
        } catch (IOException e){
            log.error(e.getLocalizedMessage());
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/books")
    public ResponseEntity getBooks(@RequestParam String parameters) {

        try {
            return ResponseEntity.ok(googleBooksService.getBooksByParameters(parameters));
        } catch (IOException ie){
            log.error(ie.getLocalizedMessage());
            return ResponseEntity.internalServerError().body(ie.getLocalizedMessage());
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {
        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
