package com.mateus.petcontrolsystem.models;

import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    private Long id;
    private Instant date;

    private Log log;
    private Client client;
    private List<Product> products = new ArrayList<>();

}
