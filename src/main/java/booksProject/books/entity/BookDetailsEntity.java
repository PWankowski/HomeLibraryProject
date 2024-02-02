package booksProject.books.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "book_details")
@Getter
@Setter
@ToString
public class BookDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String lang;
    private String publisher;

    @Column(length = 1500)
    private String description;
}
