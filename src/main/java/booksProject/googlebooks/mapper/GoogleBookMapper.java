package booksProject.googlebooks.mapper;

import booksProject.books.dto.BookDto;
import booksProject.books.mappers.BookDetailsMapper;
import booksProject.googlebooks.entity.GoogleBook;
import booksProject.googlebooks.entity.GoogleBookList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static List<BookDto> mapGooGleBookListToDtoList(GoogleBookList googleBookList) {

        return googleBookList.getItems().stream()
                                 .map(googleBook -> mapGoogleBookToBookDto(googleBook))
                                 .collect(Collectors.toList());
    }
}
