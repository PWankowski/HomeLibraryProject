package booksProject.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BookForm {

    private String title;
    private String author;

    private BookFormDetails details;
    private Set<String> tags;
}
