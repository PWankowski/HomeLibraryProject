package booksProject.books;

import java.util.Set;

public class BookDto {

    private String uuid;
    private String title;
    private String author;

    private BookDetailsDto details;
    private Set<String> tags;

    public String getUuid() {
        return uuid;
    }

    public BookDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookDetailsDto getDetails() {
        return details;
    }

    public BookDto setDetails(BookDetailsDto details) {
        this.details = details;
        return this;
    }

    public Set<String> getTags() {
        return tags;
    }

    public BookDto setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }
}
