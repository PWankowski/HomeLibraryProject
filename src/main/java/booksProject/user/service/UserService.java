package booksProject.user.service;

import booksProject.user.NoUserFoundException;
import booksProject.user.auth.AuthenticationRequest;
import booksProject.user.auth.AuthenticationResponse;
import booksProject.user.dto.UserDto;
import booksProject.user.dto.UserForm;

public interface UserService {

    UserDto getUser(String uuid) throws NoUserFoundException;

    AuthenticationResponse create(UserForm userForm);

    UserDto update(String uuid, UserForm userForm) throws NoUserFoundException;

    void delete(String uuid) throws NoUserFoundException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws NoUserFoundException;
}
