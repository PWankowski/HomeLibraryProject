package booksProject.books;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;
    private String uuid;

    private String title;

    private String author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private BookDetailsEntity details;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<BookTagEntity> tags;

    public String getUuid() {
        return uuid;
    }

    public BookEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookEntity setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookDetailsEntity getDetails() {
        return details;
    }

    public BookEntity setDetails(BookDetailsEntity details) {
        this.details = details;
        return this;
    }

    public Set<BookTagEntity> getTags() {
        return tags;
    }

    public void addBookTag(BookTagEntity bookTag){
        if (tags == null) {
            tags = new HashSet<>();
        }
        this.tags.add(bookTag);
        bookTag.getBooks().add(this);
    }

    public void removeBookTag(BookTagEntity bookTag){
        this.tags.remove(bookTag);
        bookTag.getBooks().remove(this);
    }
}
