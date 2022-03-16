package app.comun.insights.mapper;

import app.comun.insights.domain.TransactionDTO;
import app.comun.insights.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO transactionEntityToTransactionDTO(TransactionEntity transactionEntity);
    TransactionEntity transactionDTOToTransactionEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> transactionEntityListToTransactionDTOList(List<TransactionEntity> transactionEntityList);
    List<TransactionEntity> transactionDTOListToTransactionEntityList(List<TransactionDTO> transactionDTOList);
}
