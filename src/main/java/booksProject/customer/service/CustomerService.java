package booksProject.customer.service;

import booksProject.customer.dto.CustomerDto;
import booksProject.customer.dto.CustomerForm;

import java.util.List;

public interface CustomerService {

    CustomerDto getCustomer(String uuid);
    List<CustomerDto> getAllCustomers();

    CustomerDto create(CustomerForm customerForm);

    CustomerDto update(String uuid, CustomerForm customerForm);

    void delete(String uuid);
}
