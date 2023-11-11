package booksProject.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
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
