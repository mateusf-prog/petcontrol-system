package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.RegisterType;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Log {

    private Long id;
    private LocalDate date;

    private RegisterType type;

    private List<Agenda> appointments = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
}
