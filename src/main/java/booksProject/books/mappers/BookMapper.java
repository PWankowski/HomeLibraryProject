package booksProject.books.mappers;

import booksProject.books.dto.BookDto;
import booksProject.books.dto.BookForm;
import booksProject.books.entity.BookEntity;

import java.util.UUID;

public class BookMapper {


    public static BookDto map(BookEntity entity) {

        BookDto bookDto = new BookDto();
        bookDto.setUuid(entity.getUuid());
        bookDto.setAuthor(entity.getAuthor());
        bookDto.setTitle(entity.getTitle());
        bookDto.setDetails(BookDetailsMapper.map(entity.getDetails()));
        bookDto.setTags(BookTagMapper.mapToString(entity.getTags()));

        return bookDto;
    }

    public static BookEntity map(BookForm formEntity) {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setUuid(UUID.randomUUID().toString());
        bookEntity.setAuthor(formEntity.getAuthor());
        bookEntity.setTitle(formEntity.getTitle());
        bookEntity.setDetails(BookDetailsMapper.map(formEntity.getDetails()));

        return bookEntity;
    }
}
