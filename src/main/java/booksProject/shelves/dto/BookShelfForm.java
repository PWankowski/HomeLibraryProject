package booksProject.shelves.dto;

import booksProject.books.dto.BookForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BookShelfForm {

    private String name;
    private List<BookForm> items;

}
