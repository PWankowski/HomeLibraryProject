package booksProject.shelves.entity;

import booksProject.books.entity.BookEntity;
import booksProject.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
public class BookShelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookshelf_id" , nullable = false, unique = true)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @ManyToMany(mappedBy = "bookShelves")
    private List<BookEntity> items;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public BookShelf setUser(UserEntity user) {

        this.user = user;
        user.getShelves().add(this);
        return this;
    }

    public BookShelf setItems(BookEntity book) {

        if (items == null) {
            items = new ArrayList<>();
        }
        this.items.add(book);
        book.getBookShelves().add(this);
        return this;
    }

    public void removeItem(BookEntity book) {
        this.items.remove(book);
        book.getBookShelves().remove(this);
    }

    public void removeUser(UserEntity user) {
        user.getShelves().remove(this);
    }
}
