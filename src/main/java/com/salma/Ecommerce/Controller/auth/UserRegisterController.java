package com.salma.Ecommerce.Controller.auth;

import com.salma.Ecommerce.DTO.LoginResponseDTO;
import com.salma.Ecommerce.DTO.UserLoginDTO;
import com.salma.Ecommerce.DTO.UserRegisterDto;
import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Exceptions.UserAlreadyExistsException;
import com.salma.Ecommerce.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO){
        String jwt= userService.loginUser(userLoginDTO);
        if (jwt ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LoginResponseDTO loginResponseDTO=new LoginResponseDTO();
        loginResponseDTO.setJwt(jwt);
        return ResponseEntity.ok(loginResponseDTO);

    }

    @GetMapping("/user")
    public User  getUserDetails(@AuthenticationPrincipal User user){
        return user;

    }

}
