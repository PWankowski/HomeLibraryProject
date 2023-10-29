package booksProject.books;

public class NoBookFoundException extends RuntimeException{

    public NoBookFoundException(String uuid) {
        super(String.format("No Book with uuid: %s found!", uuid));
    }

}
