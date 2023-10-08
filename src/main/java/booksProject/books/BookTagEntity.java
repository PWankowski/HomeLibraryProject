package booksProject.books;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_tag")
public class BookTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tagValue;

    @ManyToMany(mappedBy = "tags")
    private Set<BookEntity> books;

    public String getTagValue() {
        return tagValue;
    }

    public BookTagEntity setTagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    public Set<BookEntity> getBooks() {
        if (books == null) {
            books = new HashSet<>();
        }
        return books;
    }

    public BookTagEntity setBooks(Set<BookEntity> books) {
        this.books = books;
        return this;
    }
}
