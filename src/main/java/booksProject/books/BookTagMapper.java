package booksProject.books;

import java.util.Set;
import java.util.stream.Collectors;

public class BookTagMapper {

    public static Set<String> maptoString(Set<BookTagEntity> bookTags){

        if(bookTags == null || bookTags.size() == 0) {
            return Set.of();
        }

        return  bookTags.stream()
                .map(BookTagEntity::getTagValue)
                .collect(Collectors.toSet());
    }

    public static Set<BookTagEntity> mapToEntity(Set<String> bookTags){

        if(bookTags == null || bookTags.size() == 0) {
            return Set.of();
        }

        return  bookTags.stream()
                .map(tag -> new BookTagEntity().setTagValue(tag))
                .collect(Collectors.toSet());
    }
}
