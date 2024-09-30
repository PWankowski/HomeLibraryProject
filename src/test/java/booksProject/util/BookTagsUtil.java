package booksProject.util;

import booksProject.books.entity.BookTagEntity;
import lombok.experimental.UtilityClass;

import java.util.LinkedHashSet;
import java.util.Set;

@UtilityClass
public class BookTagsUtil {

    private static final String TAG_VALUE_1 = "amadeus";
    private static final String TAG_VALUE_2 = "modus";
    private static final String TAG_VALUE_3 = "business";
    private static final String TAG_VALUE_4 = "ipsum";
    private static final String TAG_VALUE_5 = "dolores";


    public static Set<BookTagEntity> getBookTags() {

        BookTagEntity bookTagEntity_1 = new BookTagEntity();
        bookTagEntity_1.setTagValue(TAG_VALUE_1);
        BookTagEntity bookTagEntity_2 = new BookTagEntity();
        bookTagEntity_2.setTagValue(TAG_VALUE_2);
        BookTagEntity bookTagEntity_3 = new BookTagEntity();
        bookTagEntity_3.setTagValue(TAG_VALUE_3);

        Set<BookTagEntity> tags = new LinkedHashSet<>();
        tags.add(bookTagEntity_1);
        tags.add(bookTagEntity_2);
        tags.add(bookTagEntity_3);

        return tags;
    }

    public static Set<BookTagEntity> getBookTagsForUpdate() {

        BookTagEntity bookTagEntity_1 = new BookTagEntity();
        bookTagEntity_1.setTagValue(TAG_VALUE_4);
        BookTagEntity bookTagEntity_2 = new BookTagEntity();
        bookTagEntity_2.setTagValue(TAG_VALUE_5);

        Set<BookTagEntity> tags = new LinkedHashSet<>();
        tags.add(bookTagEntity_1);
        tags.add(bookTagEntity_2);

        return tags;
    }
}
