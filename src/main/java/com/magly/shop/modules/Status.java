package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String name;

    @OneToMany(mappedBy = "status")
    @Getter @Setter
    @JsonIgnoreProperties("status")
    Set<Orders> orderStatus = new HashSet<>();

    public Status(){}

    public Status(String name) {
        this.name = name;
    }
}
