package com.magly.shop.modules;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private String name;
    @Getter @Setter
    private double price;

    @ManyToMany
    @Getter @Setter
    List<Orders> orders;

    @ManyToMany
    List<Category> categories;
}
