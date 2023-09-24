package booksProject.books;

import java.util.UUID;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDto map(BookEntity entity) {
        return new BookDto()
                .setUuid(entity.getUuid())
                .setAuthor(entity.getAuthor())
                .setTitle(entity.getTitle())
                .setDetails(
                        BookDetailsMapper.map(entity.getDetails())
                )
                .setTags(
                        BookTagMapper.maptoString(entity.getTags())
                );
    }

    public static BookEntity map(BookForm formEntity) {
        BookEntity bookEntity = new BookEntity()
                .setUuid(UUID.randomUUID().toString())
                .setAuthor(formEntity.getAuthor())
                .setTitle(formEntity.getTitle())
                .setDetails(
                        BookDetailsMapper.map(formEntity.getDetails())
                );
        BookTagMapper.mapToEntity(formEntity.getTags())
                     .forEach(bookEntity::addBookTag);

        return bookEntity;
    }
}
