package booksProject.googlebooks.service;

import booksProject.books.dto.BookDto;
import booksProject.googlebooks.GoogleBookMapper;
import booksProject.googlebooks.GoogleClient;
import booksProject.googlebooks.entity.GoogleBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleBooksService implements GoogleBookService{

    private final GoogleClient googleClient;

    @Autowired
    public GoogleBooksService(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    public BookDto getBook(String idBook) throws IOException {

           Pair<Integer, String> result =  googleClient.getBookVolume(idBook);
           ObjectMapper objectMapper = new ObjectMapper();
           GoogleBook googleBook = objectMapper.readValue(result.getValue(), GoogleBook.class);
           return GoogleBookMapper.mapGoogleBookToBookDto(googleBook);
    }
}
