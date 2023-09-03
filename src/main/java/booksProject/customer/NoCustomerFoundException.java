package booksProject.customer;

public class NoCustomerFoundException extends RuntimeException{

    public NoCustomerFoundException(String uuid) {
        super(String.format("No Customer with uuid: %s found!", uuid));
    }
}
