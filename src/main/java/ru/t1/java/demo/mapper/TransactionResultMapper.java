package ru.t1.java.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.model.dto.TransactionForAccept;
import ru.t1.java.demo.model.dto.TransactionResult;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionResultMapper {
    TransactionResult toResultBlocked(Transaction transaction);

    TransactionResult toResult(TransactionForAccept transactionForAccept);
}
