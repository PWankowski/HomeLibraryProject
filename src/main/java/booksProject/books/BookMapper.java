package booksProject.books;

import java.util.UUID;

public class BookMapper {

    public static BookDto map(BookEntity entity) {
        BookTagMapper bookTagMapper = new BookTagMapper();
        return new BookDto()
                .setUuid(entity.getUuid())
                .setAuthor(entity.getAuthor())
                .setTitle(entity.getTitle())
                .setDetails(
                        BookDetailsMapper.map(entity.getDetails())
                )
                .setTags(
                        bookTagMapper.mapToString(entity.getTags())
                );
    }

    public static BookEntity map(BookForm formEntity) {
        BookTagMapper bookTagMapper = new BookTagMapper();

        BookEntity bookEntity = new BookEntity()
                .setUuid(UUID.randomUUID().toString())
                .setAuthor(formEntity.getAuthor())
                .setTitle(formEntity.getTitle())
                .setDetails(
                        BookDetailsMapper.map(formEntity.getDetails())
                );
        return bookEntity;
    }
}
