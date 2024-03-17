package booksProject.books.service;

import booksProject.books.BookExistException;
import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookDto;
import booksProject.books.dto.BookForm;
import booksProject.user.NoUserFoundException;

import java.util.List;

public interface BookService {

    List<BookDto> findAll(String userLogin) throws NoUserFoundException;
    List<BookDto> findAllByAuthor(String author, String userLogin) throws NoBookFoundException, NoUserFoundException;

    BookDto findByUuid(String uuid) throws NoBookFoundException;

    BookDto create(BookForm form, String userLogin) throws BookExistException, NoUserFoundException;

    boolean delete(String uuid, String userLogin) throws NoBookFoundException;

    BookDto update(String uuid, BookForm form) throws NoBookFoundException;
}
