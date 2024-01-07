package booksProject.user.service;

import booksProject.user.NoUserFoundException;
import booksProject.user.auth.AuthenticationRequest;
import booksProject.user.auth.AuthenticationResponse;
import booksProject.user.dto.UserDto;
import booksProject.user.dto.UserForm;

public interface UserService {

    UserDto getUser(String email) throws NoUserFoundException;

    AuthenticationResponse create(UserForm userForm);

    UserDto update(String email, UserForm userForm) throws NoUserFoundException;

    void delete(String email) throws NoUserFoundException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws NoUserFoundException;
}
