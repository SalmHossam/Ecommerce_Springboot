package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.DTO.UserLoginDTO;
import com.salma.Ecommerce.DTO.UserRegisterDto;
import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Entity.VerificationToken;
import com.salma.Ecommerce.Exceptions.UserAlreadyExistsException;
import com.salma.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private JWTService jwtService;


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
        VerificationToken verificationToken=createVerificationToken(user);
        return userRepository.save(user);

    }

    private VerificationToken createVerificationToken(User user){
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(jwtService.generateEmailJWT(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;

    }
    public String loginUser(UserLoginDTO userLoginDTO){
        Optional<User>loginUser=userRepository.findByUsernameIgnoreCase(userLoginDTO.getUsername());
        if(loginUser.isPresent()){
            User user=loginUser.get();
            if(encryptService.checkPassword(userLoginDTO.getPassword(),user.getPassword())){
                return jwtService.generateJWT(user);
            }

        }return null;

    }
}
