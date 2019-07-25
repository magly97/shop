package com.magly.shop.message.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class AddressForm {

    @NotBlank
    @Getter @Setter
    private String city;

    @NotBlank
    @Getter @Setter
    private String street;

    @NotBlank
    @Getter @Setter
    private String number;

    @NotBlank
    @Getter @Setter
    private String zipCode;

}
