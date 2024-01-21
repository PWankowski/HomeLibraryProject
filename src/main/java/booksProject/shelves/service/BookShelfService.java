package booksProject.shelves.service;

import booksProject.shelves.NoBookShelfExistException;
import booksProject.shelves.entity.BookShelfDto;
import booksProject.shelves.entity.BookShelfForm;

import java.util.List;


public interface BookShelfService {

     BookShelfDto createBookShelf(String userLogin, BookShelfForm shelf);

     void deleteBookShelf(Long id);

     BookShelfDto getBookShelf(Long id);

     List<BookShelfDto>  getAllByUser(String userLogin);

     BookShelfDto updateBookShelf(Long id, String name) throws NoBookShelfExistException;

     BookShelfDto addBooksToBookShelf(Long idBookShelf, List<String> booksUUIDs) throws NoBookShelfExistException;
     void deleteBookFromBookShelf(Long idBookShelf, String bookUUID) throws NoBookShelfExistException;
}
