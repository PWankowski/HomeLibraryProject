package booksProject.googlebooks.controller;

import booksProject.googlebooks.service.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
