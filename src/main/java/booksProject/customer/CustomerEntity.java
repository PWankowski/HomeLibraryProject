package booksProject.customer;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String emailAdress;

    public String getUuid() {
        return uuid;
    }

    public CustomerEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CustomerEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public CustomerEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public CustomerEntity setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public CustomerEntity setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
        return this;
    }
}
