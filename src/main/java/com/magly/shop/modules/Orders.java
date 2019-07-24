package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private String status;
    @Getter @Setter
    private Date dateStatus;

    @Getter @Setter
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    @JsonIgnoreProperties("orders")
    private Users userOrder;

    public Orders() {
    }

    public Orders(String status, Date dateStatus, Users userOrder, List<Product> products, Double price) {
        this.status = status;
        this.dateStatus = dateStatus;
        this.userOrder = userOrder;
        this.products = products;
        this.price = price;
    }

    @ManyToMany
    @JsonIgnoreProperties("orders")
    @Getter @Setter
    private List<Product> products = new ArrayList<>();

}
