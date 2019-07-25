package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private Date dateStatus;

    @Getter @Setter
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    @JsonIgnoreProperties("orders")
    private Users userOrder;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties("orderStatus")
    @Getter @Setter
    private Status status;

    @ManyToMany
    @JsonIgnoreProperties("orders")
    @Getter @Setter
    private List<Product> products;

    public Orders() {
    }
    public Orders(Status status, Date dateStatus, Users userOrder, List<Product> products, Double price) {
        this.status = status;
        this.dateStatus = dateStatus;
        this.userOrder = userOrder;
        this.products = products;
        this.price = price;
    }

}
