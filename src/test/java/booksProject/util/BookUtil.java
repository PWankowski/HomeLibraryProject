package booksProject.util;

import booksProject.books.entity.BookDetailsEntity;
import booksProject.books.entity.BookEntity;
import booksProject.books.entity.BookTagEntity;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BookUtil {

    private static final String UUID = "1234-1234-1234-1234";
    private static final String UUID2 = "1000-1000-1111-1111";
    private static final String UUID3 = "1000-0000-2222-3333";


    private static final String TITLE = "Testing";
    private static final String TITLE2 = "Testing Second Edition";
    private static final String TITLE3 = "Testing Third Edition";

    private static final String AUTHOR = "Paul Robertson";

    private static final String AUTHOR2 = "Json Hrejterzy";

    private static final String AUTHOR3 = "Json Hrejterzy";
    private static final BookDetailsEntity DETAILS = BookDetailsUtil.getBookDetails();
    private static final BookDetailsEntity DETAILS2 = BookDetailsUtil.getBookDetails2();
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

    public static Set<BookEntity> getBooks() {
        Set<BookEntity> books = new LinkedHashSet<>();

        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setUuid(UUID);
        bookEntity1.setTitle(TITLE);
        bookEntity1.setAuthor(AUTHOR);
        bookEntity1.setDetails(DETAILS);
        TAGS.forEach(tag -> bookEntity1.addBookTag(tag));

        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setUuid(UUID2);
        bookEntity2.setTitle(TITLE2);
        bookEntity2.setAuthor(AUTHOR2);
        bookEntity2.setDetails(DETAILS2);
        TAGS.forEach(tag -> bookEntity2.addBookTag(tag));

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setUuid(UUID3);
        bookEntity3.setTitle(TITLE3);
        bookEntity3.setAuthor(AUTHOR3);
        bookEntity3.setDetails(DETAILS2);
        TAGS.forEach(tag -> bookEntity3.addBookTag(tag));

        books.add(bookEntity1);
        books.add(bookEntity2);
        books.add(bookEntity3);

        return books;
    }
}