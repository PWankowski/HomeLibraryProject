package booksProject.util;

import booksProject.books.entity.BookTagEntity;

import java.util.HashSet;
import java.util.Set;

public class BookTagsUtil {

    private static final String TAG_VALUE_1 = "amadeus";

    private static final String TAG_VALUE_2 = "modus";

    private static final String TAG_VALUE_3 = "business";

    public static Set<BookTagEntity> getBookTags() {

        BookTagEntity bookTagEntity_1 = new BookTagEntity();
        bookTagEntity_1.setTagValue(TAG_VALUE_1);
        BookTagEntity bookTagEntity_2 = new BookTagEntity();
        bookTagEntity_2.setTagValue(TAG_VALUE_2);
        BookTagEntity bookTagEntity_3 = new BookTagEntity();
        bookTagEntity_3.setTagValue(TAG_VALUE_3);

        Set<BookTagEntity> tags = new HashSet<>();
        tags.add(bookTagEntity_1);
        tags.add(bookTagEntity_2);
        tags.add(bookTagEntity_3);

        return tags;
    }
}
