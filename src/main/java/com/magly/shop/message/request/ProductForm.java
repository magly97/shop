package com.magly.shop.message.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductForm {

    @NotBlank
    @Size(min = 3, max = 50)
    @Getter @Setter
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @Getter @Setter
    private Double price;

    @Getter @Setter
    private List<String> category;

}
