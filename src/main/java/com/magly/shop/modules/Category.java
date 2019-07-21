package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotNull
    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnoreProperties("categories")
    @Getter @Setter
    private List<Product> products;

}
