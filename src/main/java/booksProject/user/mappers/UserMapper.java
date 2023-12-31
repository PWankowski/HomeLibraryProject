package booksProject.user.mappers;

import booksProject.user.Role;
import booksProject.user.dto.UserDto;
import booksProject.user.dto.UserForm;
import booksProject.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static UserDto mapToDto(UserEntity customer) {

        return UserDto.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .emailAddress(customer.getEmailAddress())
                .sex(customer.getSex())
                .password(customer.getPassword())
                .login(customer.getLogin())
                .build();
    }

    public static List<UserDto> map(List<UserEntity> customerList) {

       return customerList.stream()
                .map(customer -> mapToDto(customer))
                .collect(Collectors.toList());
    }

    public static UserEntity mapToEntity(UserForm form) {

        String password = form.getPassword();
        return  UserEntity.builder()
                    .name(form.getName())
                    .surname(form.getSurname())
                    .age(form.getAge())
                    .emailAddress(form.getEmailAddress())
                    .sex(form.getSex())
                    .password(passwordEncoder.encode(password))
                    .login(form.getLogin())
                    .role(Role.USER)
                    .build();
    }
}
