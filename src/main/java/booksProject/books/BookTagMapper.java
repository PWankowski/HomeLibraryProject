package booksProject.books;

import java.util.Set;
import java.util.stream.Collectors;

public class BookTagMapper {

    public static Set<String> map(Set<BookTagEntity> bookTags){

        if(bookTags == null || bookTags.size() == 0) {
            return Set.of();
        }

        return  bookTags.stream()
                .map(tag -> tag.getTagValue())
                .collect(Collectors.toSet());
    }
}
