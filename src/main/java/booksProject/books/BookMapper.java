package booksProject.books;

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
                        BookTagMapper.map(entity.getTags())
                );
    }


}
