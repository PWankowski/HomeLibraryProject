package booksProject.shelves.entity;

import booksProject.books.dto.BookForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class BookShelfForm {

    private String name;
    private List<BookForm> items;

}
