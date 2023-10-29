package booksProject.books.mappers;


import booksProject.books.entity.BookTagEntity;

import java.util.Set;
import java.util.stream.Collectors;


public class BookTagMapper {

    public Set<String> mapToString(Set<BookTagEntity> bookTags) {

        if(bookTags == null || bookTags.size() == 0) {
            return Set.of();
        }

        return  bookTags.stream()
                .map(BookTagEntity::getTagValue)
                .collect(Collectors.toSet());
    }
}
