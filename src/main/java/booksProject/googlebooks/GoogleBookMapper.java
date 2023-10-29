package booksProject.googlebooks;

import booksProject.books.dto.BookDto;
import booksProject.books.mappers.BookDetailsMapper;
import booksProject.googlebooks.entity.GoogleBook;

import java.util.Set;

public class GoogleBookMapper {

    public static BookDto mapGoogleBookToBookDto(GoogleBook book) {

        BookDto bookDto = new BookDto();
        if(book.getVolumeInfo().getAutors() == null) {
            bookDto.setAuthor("");
        } else{
            bookDto.setAuthor(book.getVolumeInfo().getAutors().toString());
        }
        bookDto.setTitle(book.getVolumeInfo().getTitle());
        bookDto.setDetails(BookDetailsMapper.map(book));
        bookDto.setTags(Set.of(""));
        return bookDto;
    }
}
