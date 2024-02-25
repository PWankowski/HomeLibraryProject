package booksProject.books.entity;

import booksProject.shelves.entity.BookShelf;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@ToString
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;
    @Getter
    @Setter
    private String uuid;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private BookDetailsEntity details;

    @Getter
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "books_tags",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<BookTagEntity> tags;

    @Getter
    @ManyToMany
    @JoinTable(
            name = "books_bookshelves",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "bookshelves_id") }
    )
    private Set<BookShelf> bookShelves;

    public BookEntity setDetails(BookDetailsEntity details) {
        this.details = details;
        return this;
    }

    public void addBookTag(BookTagEntity bookTag) {
        if (tags == null) {
            tags = new HashSet<>();
        }
        this.tags.add(bookTag);
        bookTag.getBooks().add(this);
    }

    public void removeBookTag(BookTagEntity bookTag) {
        this.tags.remove(bookTag);
        bookTag.getBooks().remove(this);
    }

    public void removeBookShelf(BookShelf bookShelf) {
        this.bookShelves.remove(bookShelf);
        bookShelf.getItems().remove(this);
    }
}
