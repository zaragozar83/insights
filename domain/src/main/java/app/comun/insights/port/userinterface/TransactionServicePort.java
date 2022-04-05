package app.comun.insights.port.userinterface;

import app.comun.insights.domain.CardSpendMerchant;
import app.comun.insights.domain.TransactionDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionServicePort {

    TransactionDTO saveTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getTransactions();
    TransactionDTO getTransactionById(String transactionId);
    List<CardSpendMerchant> findTopCardSpendCategoriesByCustomerId(Long customerId, Optional<Integer> limitMerchant, Optional<Integer> dayAgo);
}
