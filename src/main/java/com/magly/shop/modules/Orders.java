package com.magly.shop.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String status;
    @Getter @Setter
    private Date dateStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private Users userOrder;

    @ManyToMany(mappedBy = "orders")
    @JsonIgnoreProperties("orders")
    private List<Product> products;

}
