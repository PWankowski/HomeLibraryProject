package booksProject.googlebooks.controller;

import booksProject.books.NoBookFoundException;
import booksProject.googlebooks.service.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController("/googleBooks")
public class GoogleBooksController {
    
    private final GoogleBooksService googleBooksService;

    @Autowired
    public GoogleBooksController(GoogleBooksService googleBooksService) {
        this.googleBooksService = googleBooksService;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity getBook(@PathVariable String id) {

        try {
            return ResponseEntity.ok(googleBooksService.getBook(id));
        } catch (IOException e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/book")
    public ResponseEntity getBooks(@RequestParam String parameters) {

        try {
            return ResponseEntity.ok(googleBooksService.getBooksByParameters(parameters));
        } catch (IOException ie){
            return ResponseEntity.internalServerError().body(ie.getLocalizedMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {

        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
