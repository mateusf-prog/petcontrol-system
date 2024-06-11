package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity()
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;
    private String cpfCnpj;


    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Address address;


}
