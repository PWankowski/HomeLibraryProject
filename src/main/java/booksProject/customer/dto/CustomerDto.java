package booksProject.customer.dto;

public class CustomerDto {

    private String uuid;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String emailAddress;

    public String getUuid() {
        return uuid;
    }

    public CustomerDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CustomerDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public CustomerDto setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public CustomerDto setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public CustomerDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}
