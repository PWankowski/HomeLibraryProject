package booksProject.customer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerDto mapToDto(CustomerEntity customer){
        return new CustomerDto()
                .setUuid(customer.getUuid())
                .setName(customer.getName())
                .setSurname(customer.getSurname())
                .setAge(customer.getAge())
                .setEmailAdress(customer.getEmailAddress())
                .setSex(customer.getSex());
    }

    public List<CustomerDto> map(List<CustomerEntity> customerList){
       return customerList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CustomerEntity mapToEntity(CustomerDto customerDto){
        return new CustomerEntity()
                .setUuid(UUID.randomUUID().toString())
                .setName(customerDto.getName())
                .setSurname(customerDto.getSurname())
                .setAge(customerDto.getAge())
                .setEmailAddress(customerDto.getEmailAdress())
                .setSex(customerDto.getSex());
    }


}
