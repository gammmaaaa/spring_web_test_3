package ru.t1.java.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionForAccept {

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("transaction_id")
    private Long transactionId;

    @JsonProperty("transaction_amount")
    private BigDecimal transactionAmount;

    @JsonProperty("account_balance")
    private BigDecimal accountBalance;

    @JsonProperty("time_transaction")
    private LocalDateTime timeTransaction;
}
