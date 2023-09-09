package booksProject.customer;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public CustomerDto getCustomer(String uuid) throws NoCustomerFoundException{

        return customerMapper.mapToDto(customerRepository.findByUuid(uuid).orElseThrow(() -> new NoCustomerFoundException(uuid)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDto> getAllCustomers() {

        return customerMapper.map(customerRepository.findAll());
    }

    @Override
    public CustomerDto create(CustomerForm customerForm) {

        CustomerDto customerDto = customerMapper.mapToDto(customerForm);
        CustomerEntity customerEntity = customerMapper.mapToEntity(customerDto);
        customerRepository.save(customerEntity);
        return  customerDto;
    }
    @Transactional
    @Override
    public CustomerDto update(String uuid, CustomerForm customerForm) throws NoCustomerFoundException{

        CustomerEntity result = customerRepository.findByUuid(uuid).orElseThrow(() -> new NoCustomerFoundException(uuid));

        result.setName(customerForm.getName())
                .setSurname(customerForm.getSurname())
                .setAge(customerForm.getAge())
                .setSex(customerForm.getSex())
                .setEmailAddress(customerForm.getEmailAddress());

        customerRepository.save(result);
        return customerMapper.mapToDto(result);
    }
    @Transactional
    @Override
    public void delete(String uuid) throws NoCustomerFoundException{

        CustomerEntity result = customerRepository.findByUuid(uuid).orElseThrow(() -> new NoCustomerFoundException(uuid));
        customerRepository.delete(result);
    }
}
