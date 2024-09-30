package booksProject.util;

import booksProject.books.entity.BookEntity;
import booksProject.shelves.entity.BookShelf;
import booksProject.user.Role;
import booksProject.user.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
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
    private static final Set<BookEntity> BOOKS = BookUtil.getBooks();

    private static final long ID2 = 2L;
    private static final String NAME2 = "Paul";
    private static final String SURNAME2 = "Testownik";
    private static final Set<BookEntity> BOOKS2 = BookUtil.getBooks();

    public static UserEntity getUser() {

        return new UserEntity(ID, NAME, SURNAME, AGE, SEX, EMAILADDRESS, LOGIN, PASSWORD, ROLE, SHELVES, BOOKS);
    }
    public static UserEntity getUser2() {

        return new UserEntity(ID2, NAME2, SURNAME2, AGE, SEX, EMAILADDRESS, LOGIN, PASSWORD, ROLE, SHELVES, BOOKS2);
    }


}
