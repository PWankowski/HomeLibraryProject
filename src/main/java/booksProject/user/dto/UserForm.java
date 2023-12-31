package booksProject.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserForm {
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String emailAddress;
    private String password;
    private String login;
}
