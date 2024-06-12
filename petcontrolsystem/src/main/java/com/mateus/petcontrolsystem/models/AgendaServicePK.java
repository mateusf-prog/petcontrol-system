package com.mateus.petcontrolsystem.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Embeddable
public class AgendaServicePK {

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Agenda appointment;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
