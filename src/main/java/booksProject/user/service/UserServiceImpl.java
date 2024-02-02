package booksProject.user.service;


import booksProject.configuration.security.JwtService;
import booksProject.user.*;
import booksProject.user.auth.AuthenticationRequest;
import booksProject.user.auth.AuthenticationResponse;
import booksProject.user.dto.UserDto;
import booksProject.user.dto.UserForm;
import booksProject.user.entity.UserEntity;
import booksProject.user.mappers.UserMapper;
import booksProject.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(cacheNames = "getUser", key = "#email")
    public UserDto getUser(String email) throws NoUserFoundException {

        return UserMapper.mapToDto(userRepository.findByEmailAddress(email).orElseThrow(() -> new NoUserFoundException(email)));
    }

    @Override
    public AuthenticationResponse create(UserForm userForm) throws UserExistException {
        String email =  userForm.getEmailAddress();
        String login = userForm.getLogin();
        validateIfUserExist(email,login);

        UserEntity userEntity = UserMapper.mapToEntity(userForm);
        userRepository.save(userEntity);
        String jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    @Transactional
    @Override
    @CachePut(cacheNames = "getUser", key = "#result.emailAddress")
    public UserDto update(String email, UserForm userForm) throws NoUserFoundException {

        UserEntity result = userRepository.findByEmailAddress(email).orElseThrow(() -> new NoUserFoundException(email));
        if(userForm.getName() != null) {
            result.setName(userForm.getName());
        }
        if(userForm.getSurname() != null) {
            result.setSurname(userForm.getSurname());
        }
        if(userForm.getAge() != 0) {
            result.setAge(userForm.getAge());
        }
        if(userForm.getSex() != null) {
            result.setSex(userForm.getSex());
        }
        if(userForm.getSex() != null) {
            result.setEmailAddress(userForm.getEmailAddress());
        }
        if(userForm.getLogin() != null) {
            result.setLogin(userForm.getLogin());
        }
        if(userForm.getPassword() != null) {
            result.setPassword(userForm.getPassword());
        }

        userRepository.save(result);
        return UserMapper.mapToDto(result);
    }
    @Transactional
    @Override
    public void delete(String email) throws NoUserFoundException {

        UserEntity result = userRepository.findByEmailAddress(email).orElseThrow(() -> new NoUserFoundException(email));
        userRepository.delete(result);
    }

    @Transactional
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws NoUserFoundException{
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
         );
        UserEntity user = userRepository.findByLogin(request.getLogin()).orElseThrow(() -> new NoUserFoundException(request.getLogin()));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private void validateIfUserExist(String email, String login) throws UserExistException {

        Optional<UserEntity> userByEmail = userRepository.findByEmailAddress(email);
        if(!userByEmail.isEmpty()){
            throw new UserExistException();
        }
        Optional<UserEntity> userByLogin = userRepository.findByLogin(login);
        if(!userByLogin.isEmpty()){
            throw new UserExistException();
        }
    }
}
