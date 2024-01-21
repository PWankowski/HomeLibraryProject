package booksProject.shelves.entity;

import booksProject.books.dto.BookDto;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookShelfDto {

    private Long id;
    private String name;
    private List<BookDto> items;
    private String userLogin;
}
