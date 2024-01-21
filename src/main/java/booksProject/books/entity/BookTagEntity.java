package booksProject.books.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_tag")
public class BookTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;
    @Getter
    @Setter
    private String tagValue;

    @ManyToMany(mappedBy = "tags")
    private Set<BookEntity> books;

    public Set<BookEntity> getBooks() {
        if (books == null) {
            books = new HashSet<>();
        }
        return books;
    }
}
