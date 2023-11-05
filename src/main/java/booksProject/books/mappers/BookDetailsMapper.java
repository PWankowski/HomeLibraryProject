package booksProject.books.mappers;

import booksProject.books.dto.BookDetailsDto;
import booksProject.books.dto.BookFormDetails;
import booksProject.books.entity.BookDetailsEntity;
import booksProject.googlebooks.entity.GoogleBook;

public class BookDetailsMapper {

    public static BookDetailsDto map(BookDetailsEntity bookDetails) {

        BookDetailsDto detailsDto = new BookDetailsDto();
        if(bookDetails == null) {
            return detailsDto;
        }
        detailsDto.setIsbn(bookDetails.getIsbn());
        detailsDto.setPublisher(bookDetails.getPublisher());
        detailsDto.setLang(bookDetails.getLang());
        detailsDto.setDescription(bookDetails.getDescription());

        return detailsDto;
    }

    public static BookDetailsDto map(GoogleBook googleBook) {

        BookDetailsDto detailsDto = new BookDetailsDto();
        if(googleBook == null) {
            return detailsDto;
        }
        String isbn;
        if(googleBook.getVolumeInfo().getIsbnList() == null){
            isbn = "";
        } else{
            isbn = googleBook.getVolumeInfo().getIsbnList()
                    .stream()
                    .findFirst()
                    .map(object -> {
                        return object.getType() + " " + object.getIdentifier();
                    })
                    .get();
        }
        detailsDto.setIsbn(isbn);
        detailsDto.setPublisher(googleBook.getVolumeInfo().getPublisher());
        detailsDto.setLang(googleBook.getVolumeInfo().getLanguage());
        detailsDto.setDescription(googleBook.getVolumeInfo().getDescription());

        return detailsDto;
    }

    public static BookDetailsEntity map(BookFormDetails bookFormDetails) {

        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        if(bookFormDetails == null) {
            return bookDetailsEntity;
        }
        bookDetailsEntity.setIsbn(bookFormDetails.getIsbn());
        bookDetailsEntity.setPublisher(bookFormDetails.getPublisher());
        bookDetailsEntity.setLang(bookFormDetails.getLang());
        bookDetailsEntity.setDescription(bookFormDetails.getDescription());

        return bookDetailsEntity;
    }
}
