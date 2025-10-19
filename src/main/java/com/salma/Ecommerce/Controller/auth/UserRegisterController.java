package com.salma.Ecommerce.Controller.auth;

import com.salma.Ecommerce.DTO.UserRegisterDto;
import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Exceptions.UserAlreadyExistsException;
import com.salma.Ecommerce.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        try {
            return new ResponseEntity<>(userService.registerUser(userRegisterDto), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }

}
