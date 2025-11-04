package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.DTO.UserRegisterDto;
import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Exceptions.UserAlreadyExistsException;
import com.salma.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptService encryptService;


    public User registerUser(UserRegisterDto userRegisterDto) throws UserAlreadyExistsException {
        if (userRepository.findByEmailIgnoreCase(userRegisterDto.getEmail()).isPresent()
                || userRepository.findByUsernameIgnoreCase(userRegisterDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setUsername(userRegisterDto.getUsername());
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(encryptService.encryptPassword(userRegisterDto.getPassword()));
        return userRepository.save(user);

    }
}
