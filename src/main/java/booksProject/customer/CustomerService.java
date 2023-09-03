package booksProject.customer;

import java.util.List;

public interface CustomerService {

    CustomerDto getCustomer(String uuid);
    List<CustomerDto> getAllCustomers();

    CustomerEntity create(CustomerDto customerDto);

    CustomerDto update(String uuid, CustomerDto customerDto);

    Boolean delete(String uuid);
}
