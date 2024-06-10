package com.mateus.petcontrolsystem.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AgendaService {

    private BigDecimal price;

    private Agenda appointment;
    private List<Service> services = new ArrayList<>();
}
