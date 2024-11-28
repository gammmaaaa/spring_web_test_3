package ru.t1.java.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.t1.java.demo.mapper.TransactionResultMapper;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.model.dto.TransactionForAccept;
import ru.t1.java.demo.model.dto.TransactionResult;
import ru.t1.java.demo.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

import static ru.t1.java.demo.model.enums.TransactionStatusEnum.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaTransactionAcceptConsumer {

    private final KafkaProducer kafkaProducer;
    private final TransactionService transactionService;
    private final TransactionResultMapper transactionMapper;

    @Value("${t1.kafka.topic.transaction_result}")
    private String transactionResultTopic;
    @Value("${t1.kafka.count.transactions}")
    private Integer countTransactions;

    @KafkaListener(id = "${t1.kafka.consumer.transaction.accept.group-id}",
            topics = "${t1.kafka.topic.transaction_accept}",
            containerFactory = "kafkaListenerContainerTransactionForAcceptFactory")
    @Transactional
    public void listener(@Payload TransactionForAccept message,
                         Acknowledgment ack) {
        log.debug("Transaction accept consumer: Обработка новых сообщений");

        try {
            List<Transaction> transactions = transactionService.getAllTransactionsTimeInterval(message.getTimeTransaction(), message.getClientId());

            List<Transaction> potentialBlockedTransactions = transactions.stream()
                    .filter(transaction -> List.of(ACCEPTED, REQUESTED).contains(transaction.getTransactionStatus()))
                    .toList();

            if (potentialBlockedTransactions.size() >= countTransactions) {
                potentialBlockedTransactions.stream()
                        .map(transactionMapper::toResultBlocked)
                        .forEach(transactionResult -> kafkaProducer.sendTo(transactionResultTopic, transactionResult));
            } else {
                TransactionResult transactionResult = transactionMapper.toResult(message);

                BigDecimal balance = message.getAccountBalance();
                transactionResult.setTransactionStatus(balance.compareTo(BigDecimal.ZERO) > 0 ? ACCEPTED : REJECTED);

                kafkaProducer.sendTo(transactionResultTopic, transactionResult);
            }
        } finally {
            ack.acknowledge();
        }


        log.debug("Transaction accept consumer: записи обработаны");
    }
}
