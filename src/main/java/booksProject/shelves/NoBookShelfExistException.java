package booksProject.shelves;

public class NoBookShelfExistException extends RuntimeException{

    public NoBookShelfExistException() {
        super("No BookShelf exist! ");
    }
}
