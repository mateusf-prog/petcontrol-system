package com.mateus.petcontrolsystem.dto;

import com.mateus.petcontrolsystem.models.Product;
import com.mateus.petcontrolsystem.models.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String supplier;
    private BigDecimal price;
    private String description;
    private Integer stock;
    private Category category;

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        supplier = entity.getSupplier();
        price = entity.getPrice();
        description = entity.getDescription();
        stock = entity.getStock();
        category = entity.getCategory();

    }
}
