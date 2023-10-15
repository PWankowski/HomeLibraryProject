package booksProject.books.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_details")
public class BookDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String isbn;
    @Getter
    @Setter
    private String lang;
    @Getter
    @Setter
    private String publisher;
}
