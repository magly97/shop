package com.magly.shop.message.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotBlank
    @Size(min=3, max = 60)
    @Getter @Setter
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    @Getter @Setter
    private String password;

}