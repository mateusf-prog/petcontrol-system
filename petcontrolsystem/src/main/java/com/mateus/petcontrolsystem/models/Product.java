package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {

    private Long id;
    private String name;
    private String supplier;
    private BigDecimal price;
    private String description;
    private Integer stock;

    private Category category;

    private List<Order> orders = new ArrayList<>();
}
