package booksProject.books.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDetailsDto {

    private String isbn;
    private String lang;
    private String publisher;
    private String description;
}
