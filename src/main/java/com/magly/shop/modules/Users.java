package com.magly.shop.modules;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String email;

    @OneToMany(mappedBy = "userAddress")
    @Getter @Setter
    private List<UserAddress> addresses;

    @OneToMany(mappedBy = "userOrder")
    @Getter @Setter
    private List<Orders> orders;

    @ManyToMany
    @Getter @Setter
    List<UserPermissions> userPermissions;
}
