package ru.t1.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.java.demo.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByTimeTransactionBetweenAndAccount_Client_Id(LocalDateTime timeIn, LocalDateTime timeOut, Long clientId);
}