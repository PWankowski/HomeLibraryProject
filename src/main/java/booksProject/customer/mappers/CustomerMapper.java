package booksProject.customer.mappers;

import booksProject.customer.dto.CustomerDto;
import booksProject.customer.dto.CustomerForm;
import booksProject.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public static CustomerDto mapToDto(CustomerEntity customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setUuid(customer.getUuid());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setAge(customer.getAge());
        customerDto.setEmailAddress(customer.getEmailAddress());
        customerDto.setSex(customer.getSex());
        return customerDto;
    }

    public static CustomerDto mapToDto(CustomerForm form) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setUuid(UUID.randomUUID().toString());
        customerDto.setName(form.getName());
        customerDto.setSurname(form.getSurname());
        customerDto.setAge(form.getAge());
        customerDto.setEmailAddress(form.getEmailAddress());
        customerDto.setSex(form.getSex());
        return customerDto;
    }

    public static List<CustomerDto> map(List<CustomerEntity> customerList) {

       return customerList.stream()
                .map(customer -> mapToDto(customer))
                .collect(Collectors.toList());
    }

    public static CustomerEntity mapToEntity(CustomerDto customerDto) {

        return new CustomerEntity()
                .setUuid(UUID.randomUUID().toString())
                .setName(customerDto.getName())
                .setSurname(customerDto.getSurname())
                .setAge(customerDto.getAge())
                .setEmailAddress(customerDto.getEmailAddress())
                .setSex(customerDto.getSex());
    }
}
