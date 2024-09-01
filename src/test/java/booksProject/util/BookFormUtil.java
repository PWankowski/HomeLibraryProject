package booksProject.util;

import booksProject.books.dto.BookForm;
import booksProject.books.dto.BookFormDetails;

import java.util.Set;

public class BookFormUtil {

    private static final String TITLE = "Testing in Java";
    private static final String AUTHOR = "John Simple";

    private static final BookFormDetails DETAILS = new BookFormDetails("ISBN_10 1987641221", "en", "reatespace Independent Publishing Platform", "Lorem Ipsum");
    private static final Set<String> TAGS = Set.of("ipsum", "dolores");

    public static BookForm getBookForm () {

        return new BookForm(TITLE, AUTHOR, DETAILS, TAGS);
    }
}
