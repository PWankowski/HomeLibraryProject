package booksProject.util;

import booksProject.books.entity.BookDetailsEntity;
import booksProject.books.entity.BookEntity;
import booksProject.books.entity.BookTagEntity;
import java.util.Set;

public class BookUtil {

    private static final String UUID = "1234-1234-1234-1234";

    private static final String TITLE = "Testing";

    private static final String AUTHOR = "Paul Robertson";

    private static final BookDetailsEntity DETAILS = BookDetailsUtil.getBookDetails();

    private static final Set<BookTagEntity> TAGS = BookTagsUtil.getBookTags();


    public static BookEntity getBook() {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setUuid(UUID);
        bookEntity.setTitle(TITLE);
        bookEntity.setAuthor(AUTHOR);
        bookEntity.setDetails(DETAILS);
        TAGS.forEach(tag -> bookEntity.addBookTag(tag));
        return bookEntity;
    }
}