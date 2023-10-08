package booksProject.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "getAllByAuthor")
    public List<BookDto> getAllBooksByAuthor(@RequestParam String author){
       return bookService.findAllByAuthor(author);
    }
    @GetMapping("getAll")
    public List<BookDto> getAll(){
       return bookService.findAll();
    }

    @GetMapping("getByUUID/{uuid}")
    public BookDto getBookByUUID(@PathVariable String uuid){
       return bookService.findByUuid(uuid);
    }

    @PostMapping("createBook")
    public BookDto createBook(@RequestBody BookForm formBook){
      return  bookService.create(formBook);
    }

    @DeleteMapping("deleteByUUID/{uuid}")
    public void deleteBook(@PathVariable String uuid){
       bookService.delete(uuid);
    }

    @PutMapping("updateByUUID/{uuid}")
    public BookDto updateBook(@PathVariable String uuid,@RequestBody BookForm form){
       return  bookService.update(uuid,form);
    }
}
