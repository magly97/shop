package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotNull
    @Getter @Setter
    private String name;

    @NotNull
    @Getter @Setter
    private Double price;

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties("products")
    @Getter @Setter
    List<Orders> orders = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties("products")
    @Getter @Setter
    Set<Category> categories;

    public Product() {
    }

    public Product(@NotNull String name, @NotNull Double price) {
        this.name = name;
        this.price = price;
    }
}
