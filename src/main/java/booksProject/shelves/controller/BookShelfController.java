package booksProject.shelves.controller;


import booksProject.books.NoBookFoundException;
import booksProject.shelves.BookShelfExistException;
import booksProject.shelves.NoBookShelfExistException;
import booksProject.shelves.entity.BookShelfForm;
import booksProject.shelves.service.BookShelfService;
import booksProject.user.NoUserFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookShelf/")
public class BookShelfController {

    private final BookShelfService bookShelfService;

    @Autowired
    public BookShelfController(BookShelfService bookShelfService) {
        this.bookShelfService = bookShelfService;
    }

    @PostMapping(value = "createBookShelf/{login}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBookShelf(@PathVariable String login, @RequestBody BookShelfForm bookShelfForm) {

        return ResponseEntity.ok(bookShelfService.createBookShelf(login, bookShelfForm));
    }

    @PostMapping(value = "addBooksToBookShelf/{id}")
    public ResponseEntity addBooksToBookShelf(@PathVariable Long id, @RequestBody List<String> items) {

        return ResponseEntity.ok(bookShelfService.addBooksToBookShelf(id, items));
    }

    @PutMapping(value = "updateBookShelf/{id}")
    public ResponseEntity updateBookShelf(@PathVariable Long id, @RequestParam String name) {

        return ResponseEntity.ok(bookShelfService.updateBookShelf(id, name));
    }

    @GetMapping(value = "getBookShelf/{id}")
    public ResponseEntity getBookShelf(@PathVariable Long id) {

        return ResponseEntity.ok(bookShelfService.getBookShelf(id));
    }

    @GetMapping(value = "getAllBookShelf/{login}")
    public ResponseEntity getAllBookShelf(@PathVariable String login) {

        return ResponseEntity.ok(bookShelfService.getAllByUser(login));
    }
    @DeleteMapping(value = "deleteBookFromShelf/{idBookShelf}")
    public ResponseEntity deleteBookFromShelf(@PathVariable Long idBookShelf, @RequestParam String uuidBook) {
        bookShelfService.deleteBookFromBookShelf(idBookShelf, uuidBook);
        return ResponseEntity.ok("Book Deleted");
    }

    @DeleteMapping(value = "deleteBookShelf/{idBookShelf}")
    public ResponseEntity deleteBookShelf(@PathVariable Long idBookShelf) {
        bookShelfService.deleteBookShelf(idBookShelf);
        return ResponseEntity.ok("BookShelf Deleted");
    }

    @ExceptionHandler(value = BookShelfExistException.class)
    public ResponseEntity handleBookShelfExistException(BookShelfExistException exception) {
        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoBookShelfExistException.class)
    public ResponseEntity handleBookExistException(NoBookShelfExistException exception) {

        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity handleNoUserFoundException(NoUserFoundException exception) {
        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {

        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
