package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotNull
    @Getter @Setter
    private String city;

    @NotNull
    @Getter @Setter
    private String street;

    @NotNull
    @Getter @Setter
    private String number;

    @NotNull
    @Getter @Setter
    private String zipCode;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("addresses")
    private Users userAddress;

    public UserAddress(){}

    public UserAddress(@NotNull String city, @NotNull String street, @NotNull String number, @NotNull String zipCode, Users userAddress) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.userAddress = userAddress;
    }

}
