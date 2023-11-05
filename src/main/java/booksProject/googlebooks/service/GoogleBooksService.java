package booksProject.googlebooks.service;

import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookDto;
import java.io.IOException;
import java.util.List;

public interface GoogleBooksService {

    BookDto getBook(String idBook) throws IOException, NoBookFoundException;

    List<BookDto> getBooksByParameters(String searchingParameters) throws Exception;

}
