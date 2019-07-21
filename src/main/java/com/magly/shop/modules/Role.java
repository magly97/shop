package com.magly.shop.modules;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String name;

    @ManyToMany(mappedBy = "roles")
    @Getter @Setter
    Set<Users> user;

}
