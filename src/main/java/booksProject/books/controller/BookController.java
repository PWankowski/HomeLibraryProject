package booksProject.books.controller;

import booksProject.books.BookExistException;
import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookForm;
import booksProject.books.service.BookService;
import booksProject.user.NoUserFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getAllBooksByAuthor(@RequestParam String author, @RequestParam String userLogin) {

       return ResponseEntity.ok(bookService.findAllByAuthor(author, userLogin));
    }
    @GetMapping("getAll/{userLogin}")
    public ResponseEntity getAll(@PathVariable String userLogin) {

       return ResponseEntity.ok(bookService.findAll(userLogin));
    }

    @GetMapping("getByUUID/{uuid}")
    public ResponseEntity getBookByUUID(@PathVariable String uuid) {

       return ResponseEntity.ok(bookService.findByUuid(uuid));
    }

    @PostMapping("createBook")
    public ResponseEntity createBook(@RequestBody BookForm formBook, @RequestParam String userLogin) {

      return  ResponseEntity.ok(bookService.create(formBook, userLogin));
    }

    @DeleteMapping("deleteByUUID")
    public ResponseEntity deleteBook(@RequestParam String uuid, @RequestParam String userLogin) {

      return   ResponseEntity.ok(bookService.delete(uuid, userLogin));
    }

    @PutMapping("updateByUUID/{uuid}")
    public ResponseEntity updateBook(@PathVariable String uuid, @RequestBody BookForm form) {

       return  ResponseEntity.ok(bookService.update(uuid,form));
    }

    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {

        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookExistException.class)
    public ResponseEntity handleBookExistException(BookExistException exception) {

        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity handleNoUserFoundException(NoUserFoundException exception) {

        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
