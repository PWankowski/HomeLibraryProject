package booksProject.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class BookDto {

    private String uuid;
    private String title;
    private String author;

    private BookDetailsDto details;
    private Set<String> tags;
}
