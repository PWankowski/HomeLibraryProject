package booksProject.shelves.mapper;

import booksProject.books.mappers.BookMapper;
import booksProject.shelves.entity.BookShelf;
import booksProject.shelves.entity.BookShelfDto;
import booksProject.shelves.entity.BookShelfForm;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookShelfMapper {

    public static BookShelf mapBookShelfFormToBookShelf(BookShelfForm form) {

        BookShelf bookShelf = new BookShelf();
        bookShelf.setName(form.getName());
        return bookShelf;
    }

    public static BookShelfDto mapBookShelfToBookShelfDto(BookShelf shelf) {
        BookShelfDto bookShelfDto = new BookShelfDto();
        bookShelfDto.setId(shelf.getId());
        bookShelfDto.setName(shelf.getName());
        if(shelf.getItems() == null || shelf.getItems().isEmpty()) {
            bookShelfDto.setItems(new ArrayList<>());
        } else {
            bookShelfDto.setItems(shelf.getItems().stream()
                    .map(shelfItems -> BookMapper.map(shelfItems))
                    .collect(Collectors.toList()));
        }
        bookShelfDto.setUserLogin(shelf.getUser().getLogin());
       return bookShelfDto;
    }

}
