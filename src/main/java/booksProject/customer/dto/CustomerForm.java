package booksProject.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CustomerForm {
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String emailAddress;

}
