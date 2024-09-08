package booksProject.util;

import booksProject.books.entity.BookEntity;
import booksProject.shelves.entity.BookShelf;
import booksProject.user.Role;
import booksProject.user.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class UserUtil {

    private static final long ID = 1L;
    private static final String NAME = "Jan";
    private static final String SURNAME = "Testowy";
    private static final int AGE = 33;
    private static final String SEX = "M";

    private static final String EMAILADDRESS = "test1@gmail.com";

    private static final String LOGIN = "Test";
    private static final String PASSWORD = "Test12345";

    private static final Role ROLE = Role.USER;

    private static final Set<BookShelf> SHELVES = new HashSet<>();

    private static final Set<BookEntity> BOOKS = Set.of(BookUtil.getBook());
    public static UserEntity getUser() {

        return new UserEntity(ID, NAME, SURNAME, AGE, SEX, EMAILADDRESS,LOGIN, PASSWORD, ROLE, SHELVES, BOOKS);
    }

}
