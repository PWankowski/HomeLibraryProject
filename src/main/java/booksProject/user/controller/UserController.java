package booksProject.user.controller;


import booksProject.user.dto.UserForm;
import booksProject.user.service.UserService;
import booksProject.user.NoUserFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{email}")
    public ResponseEntity getUser(@PathVariable String email) {

         return ResponseEntity.ok(userService.getUser(email));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity updateUser(@RequestBody UserForm userForm, @PathVariable String email) {

        return ResponseEntity.ok(userService.update(email, userForm));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUser(@PathVariable String email) {
        try{
            userService.delete(email);
            return ResponseEntity.ok(String.format("User with email: %s deleted", email));
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            return new ResponseEntity(String.format("User with email: %s wasn't deleted", email), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity handleNoUserFoundException(NoUserFoundException exception) {
        log.warn(exception.getLocalizedMessage());
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
