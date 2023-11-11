package booksProject.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {

    private String uuid;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String emailAddress;

}
