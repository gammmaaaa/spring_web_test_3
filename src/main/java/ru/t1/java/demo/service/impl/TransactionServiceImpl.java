package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.repository.TransactionRepository;
import ru.t1.java.demo.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    @Value("${t1.kafka.count.time}")
    private Long countTime;

    @Override
    public List<Transaction> getAllTransactionsTimeInterval(LocalDateTime timeTransaction, Long clientId) {
        return transactionRepository.findAllByTimeTransactionBetweenAndAccount_Client_Id
                (timeTransaction.minusMinutes(countTime), timeTransaction.plusNanos(1), clientId);
    }
}
