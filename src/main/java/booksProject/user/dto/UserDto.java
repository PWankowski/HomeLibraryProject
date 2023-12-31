package booksProject.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private String name;
    private String surname;
    private int age;
    private String sex;
    private String emailAddress;
    private String login;
    private String password;

}
