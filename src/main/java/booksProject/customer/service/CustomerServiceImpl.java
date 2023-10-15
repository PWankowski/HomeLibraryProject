package booksProject.customer.service;


import booksProject.customer.*;
import booksProject.customer.dto.CustomerDto;
import booksProject.customer.dto.CustomerForm;
import booksProject.customer.entity.CustomerEntity;
import booksProject.customer.mappers.CustomerMapper;
import booksProject.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDto getCustomer(String uuid) throws NoCustomerFoundException {

        return CustomerMapper.mapToDto(customerRepository.findByUuid(uuid).orElseThrow(() -> new NoCustomerFoundException(uuid)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDto> getAllCustomers() {

        return CustomerMapper.map(customerRepository.findAll());
    }

    @Override
    public CustomerDto create(CustomerForm customerForm) {

        CustomerDto customerDto = CustomerMapper.mapToDto(customerForm);
        CustomerEntity customerEntity = CustomerMapper.mapToEntity(customerDto);
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
        return CustomerMapper.mapToDto(result);
    }
    @Transactional
    @Override
    public void delete(String uuid) throws NoCustomerFoundException{

        CustomerEntity result = customerRepository.findByUuid(uuid).orElseThrow(() -> new NoCustomerFoundException(uuid));
        customerRepository.delete(result);
    }
}
