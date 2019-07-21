package com.magly.shop.modules;

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
    @JoinColumn(name = "user_id")
    private Users userAddress;

}
