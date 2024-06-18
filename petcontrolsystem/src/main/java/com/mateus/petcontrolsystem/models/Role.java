package com.mateus.petcontrolsystem.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode(of = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roles;

    @Override
    public String getAuthority() {
        return roles;
    }
}
