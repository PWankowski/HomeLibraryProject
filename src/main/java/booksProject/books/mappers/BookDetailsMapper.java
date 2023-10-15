package booksProject.books.mappers;

import booksProject.books.dto.BookDetailsDto;
import booksProject.books.dto.BookFormDetails;
import booksProject.books.entity.BookDetailsEntity;

public class BookDetailsMapper {

    public static BookDetailsDto map(BookDetailsEntity bookDetails){
        BookDetailsDto detailsDto = new BookDetailsDto();
        if(bookDetails == null) {
            return detailsDto;
        }
        detailsDto.setIsbn(bookDetails.getIsbn());
        detailsDto.setPublisher(bookDetails.getPublisher());
        detailsDto.setLang(bookDetails.getLang());

        return detailsDto;
    }

    public static BookDetailsEntity map(BookFormDetails bookFormDetails){
        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        if(bookFormDetails == null) {
            return bookDetailsEntity;
        }
        bookDetailsEntity.setIsbn(bookFormDetails.getIsbn());
        bookDetailsEntity.setPublisher(bookFormDetails.getPublisher());
        bookDetailsEntity.setLang(bookFormDetails.getLang());
        return bookDetailsEntity;
    }
}
