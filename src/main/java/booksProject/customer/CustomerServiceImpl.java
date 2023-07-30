package booksProject.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto getCustomer(String uuid) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return null;
    }

    @Override
    public CustomerEntity create(CustomerDto customerDto) {
        return null;
    }

    @Override
    public CustomerDto update(String uuid, CustomerDto customerDto) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }
}
