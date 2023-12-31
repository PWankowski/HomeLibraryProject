package booksProject.user.controller;

import booksProject.user.auth.AuthenticationRequest;
import booksProject.user.auth.AuthenticationResponse;
import booksProject.user.dto.UserForm;
import booksProject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegistryController {

    private final UserService userService;

    @Autowired
    public RegistryController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserForm userForm) {

        return ResponseEntity.ok(userService.create(userForm));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(userService.authenticate(request));
    }
}
