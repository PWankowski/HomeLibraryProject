package booksProject.books;

import jakarta.persistence.*;

@Entity
@Table(name = "book_details")
public class BookDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String lang;
    private String publisher;

    public String getIsbn() {
        return isbn;
    }

    public BookDetailsEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public BookDetailsEntity setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookDetailsEntity setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }
}
