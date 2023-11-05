package booksProject.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDetailsDto {

    private String isbn;
    private String lang;
    private String publisher;
    private String description;
}
