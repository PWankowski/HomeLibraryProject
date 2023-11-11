package booksProject.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookFormDetails {

    private String isbn;
    private String lang;
    private String publisher;
    private String description;
}
