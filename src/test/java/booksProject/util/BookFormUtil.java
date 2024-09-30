package booksProject.util;

import booksProject.books.dto.BookForm;
import booksProject.books.dto.BookFormDetails;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class BookFormUtil {

    private static final String TITLE = "Red Hat";
    private static final String AUTHOR = "Peter Jackson";
    private static final BookFormDetails DETAILS = new BookFormDetails("ISBN_10 1987641221", "en", "Createspace Independent Publishing Platform", "Lorem Ipsum");
    private static final Set<String> TAGS = Set.of("ipsum", "dolores");

    public static BookForm getBookForm() {

        return new BookForm(TITLE, AUTHOR, DETAILS, TAGS);
    }
}
