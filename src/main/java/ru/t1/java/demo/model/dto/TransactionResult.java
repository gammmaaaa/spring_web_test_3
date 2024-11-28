package ru.t1.java.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.java.demo.model.enums.TransactionStatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResult {
    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("transaction_id")
    private Long transactionId;

    @JsonProperty("transaction_status")
    private TransactionStatusEnum transactionStatus;
}
