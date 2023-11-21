package booksProject.books.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {

    private String uuid;
    private String title;
    private String author;

    private BookDetailsDto details;
    private Set<String> tags;
}
