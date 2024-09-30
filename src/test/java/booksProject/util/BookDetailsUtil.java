package booksProject.util;

import booksProject.books.entity.BookDetailsEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookDetailsUtil {

    private static final String ISBN = "ISBN_10 1987641221";
    private static final String PUBLISHER = "Createspace Independent Publishing Platform";
    private static final String LANG = "en";
    private static final String DESCRIPTION = "3 of the 2586 sweeping interview questions in this book, revealed: Toughness question: What advice or Java Developer suggestions would you give to aspiring high achievers to help them become more resilient and thrive on the types of situations you have been discussing? - Culture Fit question: What other commitments do you have in your Java Developer life ... ";


    private static final String ISBN2 = "ISBN_10 0006";
    private static final String LANG2 = "en";
    private static final String PUBLISHER2 = "RedBook";
    private static final String DESCRIPTION2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco";


    private static final String ISBN3 = "ISBN_10 1987641221";
    private static final String LANG3 = "en";
    private static final String PUBLISHER3 = "Createspace Independent Publishing Platform";
    private static final String DESCRIPTION3 = "Lorem Ipsum";

    public static BookDetailsEntity getBookDetails() {

        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn(ISBN);
        bookDetailsEntity.setPublisher(PUBLISHER);
        bookDetailsEntity.setLang(LANG);
        bookDetailsEntity.setDescription(DESCRIPTION);
        return bookDetailsEntity;
    }

    public static BookDetailsEntity getBookDetails2() {

        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn(ISBN2);
        bookDetailsEntity.setPublisher(PUBLISHER2);
        bookDetailsEntity.setLang(LANG2);
        bookDetailsEntity.setDescription(DESCRIPTION2);
        return bookDetailsEntity;
    }

    public static BookDetailsEntity getBookDetailsForUpdate() {

        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn(ISBN3);
        bookDetailsEntity.setPublisher(PUBLISHER3);
        bookDetailsEntity.setLang(LANG3);
        bookDetailsEntity.setDescription(DESCRIPTION3);
        return bookDetailsEntity;
    }
}
