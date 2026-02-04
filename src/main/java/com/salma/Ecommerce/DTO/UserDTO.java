package com.salma.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String email;
}
