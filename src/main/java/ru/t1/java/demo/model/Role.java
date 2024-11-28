package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(name = "ID_GEN", sequenceName = "role_seq", allocationSize = 1)
    @GeneratedValue(generator = "ID_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

}