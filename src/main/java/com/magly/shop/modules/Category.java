package com.magly.shop.modules;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
