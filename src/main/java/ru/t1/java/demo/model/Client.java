package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client extends EntityObject {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "blocked_for")
    private Boolean blockedFor;

    @Column(name = "blocked_whom")
    private String blockedWhom;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();

}