package com.magly.shop.modules;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private String city;
    @Getter @Setter
    private String street;
    @Getter @Setter
    private String number;
    @Getter @Setter
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userAddress;

}
