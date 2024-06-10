package com.mateus.petcontrolsystem.models;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {

    private Long id;
    private LocalDate date;

    private Log log;
    private Client client;
    private List<Service> services = new ArrayList<>();

}
