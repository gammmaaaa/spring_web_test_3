package ru.t1.java.demo.service;

import ru.t1.java.demo.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactionsTimeInterval(LocalDateTime timeTransaction, Long clientId);
}
