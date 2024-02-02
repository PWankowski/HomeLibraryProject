package booksProject.user;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException(String data) {
        super(String.format("No User  %s found!", data));
    }
}
