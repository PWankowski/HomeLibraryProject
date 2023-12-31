package booksProject.user;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException(String uuid) {
        super(String.format("No User with email: %s found!", uuid));
    }
}
