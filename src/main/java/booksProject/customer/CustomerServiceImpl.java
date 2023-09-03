package booksProject.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getCustomer(String uuid) throws NoCustomerFoundException{

        return customerMapper.mapToDto(customerRepository.findByUuid(uuid).orElseThrow(() -> new NoCustomerFoundException(uuid)));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.map(customerRepository.findAll());
    }

    @Override
    public CustomerEntity create(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.mapToEntity(customerDto);
        return customerRepository.save(customerEntity);

    }

    @Override
    public CustomerDto update(String uuid, CustomerDto customerDto) {
        Optional<CustomerEntity> result = customerRepository.findByUuid(uuid);
        if(result.isPresent()){
            return customerMapper.mapToDto(result.get());
        }
        return new CustomerDto();
    }

    @Override
    public Boolean delete(String uuid) {
        Optional<CustomerEntity> result = customerRepository.findByUuid(uuid);
        if(result.isPresent()){
            customerRepository.delete(result.get());
            return true;
        }
        return false;
    }
}
