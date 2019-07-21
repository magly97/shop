package com.magly.shop.message.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

public class SignUpForm {

    @NotBlank
    @Size(min = 3, max = 50)
    @Getter @Setter
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 50)
    @Getter @Setter
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 50)
    @Getter @Setter
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    @Getter @Setter
    private String email;

    @Getter @Setter
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    @Getter @Setter
    private String password;

}