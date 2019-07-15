package com.magly.shop.modules;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserPermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String type;

    @ManyToMany(mappedBy = "userPermissions")
    @Getter @Setter
    List<Users> user;

}
