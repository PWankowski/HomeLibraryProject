package booksProject.books.controller;

import booksProject.books.BookExistException;
import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookDto;
import booksProject.books.dto.BookForm;
import booksProject.books.service.BookService;
import booksProject.user.NoUserFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("getAllByAuthor")
    public ResponseEntity getAllBooksByAuthor(@RequestParam String author, @RequestHeader("User-Login") String userLogin) {

        List<BookDto> resultList = bookService.findAllByAuthor(author, userLogin);
        return ResponseEntity.ok(resultList);
    }
    @GetMapping("getAll")
    public ResponseEntity getAll(@RequestHeader("User-Login") String userLogin) {

        List<BookDto> resultList = bookService.findAll(userLogin);
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("getByUUID/{uuid}")
    public ResponseEntity getBookByUUID(@PathVariable String uuid) {

        BookDto book = bookService.findByUuid(uuid);
        return ResponseEntity.ok(book);
    }

    @PostMapping("createBook")
    public ResponseEntity createBook(@RequestBody BookForm formBook, @RequestHeader("User-Login") String userLogin) {

        BookDto book = bookService.create(formBook, userLogin);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("deleteByUUID")
    public ResponseEntity deleteBook(@RequestParam String uuid, @RequestHeader("User-Login") String userLogin) {

        bookService.delete(uuid, userLogin);
        return ResponseEntity.ok("Poprawnie usnięto obiekt");
    }

    @PutMapping("updateByUUID/{uuid}")
    public ResponseEntity updateBook(@PathVariable String uuid, @RequestBody BookForm form) {

        BookDto book = bookService.update(uuid,form);
        return ResponseEntity.ok(book);
    }

    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {

        log.warn(exception.getLocalizedMessage(), exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(value = BookExistException.class)
    public ResponseEntity handleBookExistException(BookExistException exception) {

        log.warn(exception.getLocalizedMessage(), exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity handleNoUserFoundException(NoUserFoundException exception) {

        log.warn(exception.getLocalizedMessage(), exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
