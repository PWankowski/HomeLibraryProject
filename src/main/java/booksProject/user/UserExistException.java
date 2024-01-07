package booksProject.user;

public class UserExistException extends RuntimeException {

    public UserExistException() {
        super("User with this email/login exist!");
    }
}
