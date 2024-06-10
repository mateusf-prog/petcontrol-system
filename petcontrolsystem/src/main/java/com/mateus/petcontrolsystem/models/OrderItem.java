package com.mateus.petcontrolsystem.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItem {

    private Integer quantity;
    private BigDecimal price;

    private Order order;
    private List<Product> products = new ArrayList<>();
}
