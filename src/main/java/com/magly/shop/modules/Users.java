package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotNull
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @NotNull
    @Getter @Setter
    private String password;

    @Email @NotNull
    @Getter @Setter
    private String email;

    @OneToMany(mappedBy = "userAddress")
    @JsonIgnoreProperties("userAddress")
    @Getter @Setter
    private Set<UserAddress> addresses;

    @OneToMany(mappedBy = "userOrder")
    @Getter @Setter
    @JsonIgnoreProperties("userOrder")
    private List<Orders> orders;

    @ManyToMany
    @Getter @Setter
    @JsonIgnoreProperties("user")
    Set<Role> roles = new HashSet<>();

    public Users() {
    }

    public Users(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
