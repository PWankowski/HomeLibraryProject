package booksProject.googlebooks.service;

import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookDto;
import booksProject.googlebooks.mapper.GoogleBookMapper;
import booksProject.googlebooks.GoogleClient;
import booksProject.googlebooks.entity.GoogleBook;
import booksProject.googlebooks.entity.GoogleBookList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class GoogleBooksServiceImpl implements GoogleBooksService {

    private final GoogleClient googleClient;

    @Autowired
    public GoogleBooksServiceImpl(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    public BookDto getBook(String idBook) throws IOException, NoBookFoundException {

           Pair<Integer, String> result =  googleClient.getBookVolume(idBook);
            if(result.getKey() != HttpStatus.OK.value()) {
                throw new NoBookFoundException();
            }
           ObjectMapper objectMapper = new ObjectMapper();
           GoogleBook googleBook = objectMapper.readValue(result.getValue(), GoogleBook.class);
           return GoogleBookMapper.mapGoogleBookToBookDto(googleBook);
    }

    @Override
    public List<BookDto> getBooksByParameters(String searchingParameters) throws Exception {

        Pair<Integer, String> result =  googleClient.getBooksList(searchingParameters);
        if(result.getKey() != HttpStatus.OK.value()) {
            throw new Exception(String.format("Problem with request, code: %d  message: %s", result.getKey(), result.getValue()));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        GoogleBookList googleBook = objectMapper.readValue(result.getValue(), GoogleBookList.class);
        if(googleBook.getTotalItems() == BigDecimal.ZERO.intValue()) {
            throw new NoBookFoundException();
        }
        return GoogleBookMapper.mapGooGleBookListToDtoList(googleBook);
    }
}
