package com.mateus.petcontrolsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    // todo falta o relacionamento - agenda, client, log
//    private Log log;
//    private Client client;

    @OneToMany(mappedBy = "id.appointment")
    private List<AgendaService> services = new ArrayList<>();

    public List<Service> getServices() {
        return services.stream().map(AgendaService::getService).toList();
    }


}
