package com.mateus.petcontrolsystem.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_agenda_service")
public class AgendaService {

    @EmbeddedId
    private AgendaServicePK id;

    public AgendaService(Agenda appointment,Service service) {
        id.setService(service);
        id.setAppointment(appointment);
    }

    public Service getService() {
        return id.getService();
    }

    public void setService(Service service) {
        id.setService(service);
    }

    public Agenda getAppointment() {
        return id.getAppointment();
    }

    public void setAppointment(Agenda appointment) {
        id.setAppointment(appointment);
    }
}
