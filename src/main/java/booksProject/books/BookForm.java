package booksProject.books;

import java.util.Set;

public class BookForm {
    private String title;
    private String author;

    private BookFormDetails details;
    private Set<String> tags;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookFormDetails getDetails() {
        return details;
    }

    public Set<String> getTags() {
        return tags;
    }
}
