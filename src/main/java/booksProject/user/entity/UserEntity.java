package booksProject.user.entity;


import booksProject.books.entity.BookEntity;
import booksProject.shelves.entity.BookShelf;
import booksProject.user.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_entity")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private int age;
    private String sex;
    @Column(name = "email_address")
    private String emailAddress;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<BookShelf> shelves;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<BookEntity> books;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void removeBook(BookEntity book) {
        this.books.remove(book);
    }

}
