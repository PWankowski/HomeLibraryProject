package booksProject.util;

import booksProject.books.entity.BookDetailsEntity;

public class BookDetailsUtil {

    private static final String ISBN = "ISBN_10 1987641221";
    private static final String LANG = "en";
    private static final String PUBLISHER = "Createspace Independent Publishing Platform";
    private static final String DESCRIPTION = "3 of the 2586 sweeping interview questions in this book, revealed: Toughness question: What advice or Java Developer suggestions would you give to aspiring high achievers to help them become more resilient and thrive on the types of situations you have been discussing? - Culture Fit question: What other commitments do you have in your Java Developer life ... ";

    public static BookDetailsEntity getBookDetails() {

        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn(ISBN);
        bookDetailsEntity.setPublisher(PUBLISHER);
        bookDetailsEntity.setLang(LANG);
        bookDetailsEntity.setDescription(DESCRIPTION);
        return bookDetailsEntity;
    }
}
