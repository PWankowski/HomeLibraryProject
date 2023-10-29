package booksProject.googlebooks.service;

import booksProject.books.dto.BookDto;
import booksProject.googlebooks.entity.GoogleBook;

import java.io.IOException;

public interface GoogleBookService {

    BookDto getBook(String idBook) throws IOException;

}
