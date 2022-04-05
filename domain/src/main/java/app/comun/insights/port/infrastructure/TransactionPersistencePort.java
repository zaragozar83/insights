package app.comun.insights.port.infrastructure;

import app.comun.insights.domain.TransactionDTO;

import java.util.List;

public interface TransactionPersistencePort {

    TransactionDTO saveTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getTransactions();
    TransactionDTO getTransactionById(String transactionId);

    List<TransactionDTO> findTransactionsByCustomerId(Long customerId);
}
