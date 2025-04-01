package booksProject.shelves.controller;


import booksProject.books.NoBookFoundException;
import booksProject.shelves.BookShelfExistException;
import booksProject.shelves.NoBookShelfExistException;
import booksProject.shelves.dto.BookShelfDto;
import booksProject.shelves.dto.BookShelfForm;
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

    @PostMapping(value = "createBookShelf", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBookShelf(@RequestHeader("User-Login") String login, @RequestBody BookShelfForm bookShelfForm) {

        BookShelfDto bookShelf = bookShelfService.createBookShelf(login, bookShelfForm);
        return ResponseEntity.ok(bookShelf);
    }

    @PostMapping(value = "addBooksToBookShelf/{idBookShelf}")
    public ResponseEntity addBooksToBookShelf(@PathVariable Long idBookShelf, @RequestBody List<String> items) {

        BookShelfDto bookShelf = bookShelfService.addBooksToBookShelf(idBookShelf, items);
        return ResponseEntity.ok(bookShelf);
    }

    @PutMapping(value = "updateBookShelf/{idBookShelf}")
    public ResponseEntity updateBookShelf(@PathVariable Long idBookShelf, @RequestParam String name) {

        BookShelfDto bookShelf = bookShelfService.updateBookShelf(idBookShelf, name);
        return ResponseEntity.ok(bookShelf);
    }

    @GetMapping(value = "getBookShelf/{idBookShelf}")
    public ResponseEntity getBookShelf(@PathVariable Long idBookShelf) {

        BookShelfDto bookShelf = bookShelfService.getBookShelf(idBookShelf);
        return ResponseEntity.ok(bookShelf);
    }

    @GetMapping(value = "getAllBookShelf")
    public ResponseEntity getAllBookShelf(@RequestHeader("User-Login") String login) {

        List<BookShelfDto> bookShelflist = bookShelfService.getAllByUser(login);
        return ResponseEntity.ok(bookShelflist);
    }
    @DeleteMapping(value = "deleteBookFromShelf/{idBookShelf}")
    public ResponseEntity deleteBookFromShelf(@PathVariable Long idBookShelf, @RequestParam String uuidBook) {

        bookShelfService.deleteBookFromBookShelf(idBookShelf, uuidBook);
        return ResponseEntity.ok("Book deleted");
    }

    @DeleteMapping(value = "deleteBookShelf/{idBookShelf}")
    public ResponseEntity deleteBookShelf(@PathVariable Long idBookShelf) {

        bookShelfService.deleteBookShelf(idBookShelf);
        return ResponseEntity.ok("BookShelf Deleted");
    }

    @ExceptionHandler(value = BookShelfExistException.class)
    public ResponseEntity handleBookShelfExistException(BookShelfExistException exception) {

        log.warn(exception.getLocalizedMessage());
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(value = NoBookShelfExistException.class)
    public ResponseEntity handleBookExistException(NoBookShelfExistException exception) {

        log.warn(exception.getLocalizedMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity handleNoUserFoundException(NoUserFoundException exception) {

        log.warn(exception.getLocalizedMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {

        log.warn(exception.getLocalizedMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
