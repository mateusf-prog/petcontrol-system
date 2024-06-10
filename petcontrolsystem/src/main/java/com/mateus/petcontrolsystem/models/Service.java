package com.mateus.petcontrolsystem.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Service {

    private Long id;
    private String name;
    private BigDecimal smallDogPrice;
    private BigDecimal mediumSizeDogPrice;
    private BigDecimal bigDogPrice;

    private List<Agenda> appointments = new ArrayList<>();
}
