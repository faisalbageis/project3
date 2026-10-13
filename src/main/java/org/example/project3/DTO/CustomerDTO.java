package org.example.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "username can not be empty")
    @Size(min = 4, max = 10,message = "username length must be between 4 and 10")
    private String username;

    @NotEmpty(message = "password can not be null")
    @Size(min = 6,message = "password length must be at least 6")
    private String password;

    @NotEmpty(message = "name can not be null")
    @Size(min = 2,max = 20,message = "name length must be between 2 and 20 ")
    private String name;

    @Email
    private String email;


    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN",message = "Role must be ether CUSTOMER or EMPLOYEE or ADMIN")
    private String Role;

    @NotEmpty(message = "phoneNumber can not be empty")
    @Pattern(regexp = "^05\\d{8}$",message = "Phone number must start with 05 and contain 10 digits")
    private String phoneNumber;
}
