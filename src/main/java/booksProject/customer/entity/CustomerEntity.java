package booksProject.customer.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String uuid;
    private String name;
    private String surname;
    private int age;
    private String sex;
    @Column(name = "email_address")
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public CustomerEntity setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}
