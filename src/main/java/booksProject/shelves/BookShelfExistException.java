package booksProject.shelves;

public class BookShelfExistException extends RuntimeException {

    public BookShelfExistException() {
        super("BookShelf with this name Exist");
    }
}
