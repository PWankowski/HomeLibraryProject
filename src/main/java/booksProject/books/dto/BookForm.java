package booksProject.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@Getter
@ToString
public class BookForm {
    private String title;
    private String author;

    private BookFormDetails details;
    private Set<String> tags;
}
