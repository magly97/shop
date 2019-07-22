package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @ManyToMany
    @JsonIgnoreProperties("products")
    @Getter @Setter
    List<Orders> orders;

    @ManyToMany
    @JsonIgnoreProperties("products")
    @Getter @Setter
    List<Category> categories;

}
