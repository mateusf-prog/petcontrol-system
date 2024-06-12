package com.mateus.petcontrolsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal smallDogPrice;
    private BigDecimal mediumSizeDogPrice;
    private BigDecimal bigDogPrice;

    @OneToMany(mappedBy = "id.service")
    private List<AgendaService> items = new ArrayList<>();

    public List<Agenda> getAppointments() {
        return items.stream().map(AgendaService::getAppointment).toList();
    }
}
