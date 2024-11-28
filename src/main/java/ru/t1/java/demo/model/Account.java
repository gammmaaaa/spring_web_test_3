package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.*;
import ru.t1.java.demo.model.enums.AccountStatusEnum;
import ru.t1.java.demo.model.enums.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account extends EntityObject {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatus;

    @Column(name = "balance", precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "frozen_amount", precision = 19, scale = 2)
    private BigDecimal frozenAmount;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
