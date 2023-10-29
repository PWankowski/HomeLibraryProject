package booksProject.books.controller;

import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookForm;
import booksProject.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("getAllByAuthor")
    public ResponseEntity getAllBooksByAuthor(@RequestParam String author) {

       return ResponseEntity.ok(bookService.findAllByAuthor(author));
    }
    @GetMapping("getAll")
    public ResponseEntity getAll() {

       return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("getByUUID/{uuid}")
    public ResponseEntity getBookByUUID(@PathVariable String uuid) {

       return ResponseEntity.ok(bookService.findByUuid(uuid));
    }

    @PostMapping("createBook")
    public ResponseEntity createBook(@RequestBody BookForm formBook) {

      return  ResponseEntity.ok(bookService.create(formBook));
    }

    @DeleteMapping("deleteByUUID/{uuid}")
    public ResponseEntity deleteBook(@PathVariable String uuid) {

      return   ResponseEntity.ok(bookService.delete(uuid));
    }

    @PutMapping("updateByUUID/{uuid}")
    public ResponseEntity updateBook(@PathVariable String uuid, @RequestBody BookForm form) {

       return  ResponseEntity.ok(bookService.update(uuid,form));
    }

    @ExceptionHandler(value = NoBookFoundException.class)
    public ResponseEntity handleNoBookFoundException(NoBookFoundException exception) {

        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
