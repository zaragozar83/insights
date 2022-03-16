package app.comun.insights.port.service;

import app.comun.insights.domain.CardSpendMerchant;
import app.comun.insights.domain.TransactionDTO;
import app.comun.insights.port.infrastructure.TransactionPersistencePort;
import app.comun.insights.port.userinterface.TransactionServicePort;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionServicePort {

    private final TransactionPersistencePort transactionPersistencePort;

    public TransactionServiceImpl(TransactionPersistencePort transactionPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        return transactionPersistencePort.saveTransaction(transactionDTO);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionPersistencePort.getTransactions();
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        return transactionPersistencePort.getTransactionById(transactionId);
    }

    @Override
    public List<CardSpendMerchant> findTopCardSpendCategoriesByCustomerId(Long customerId, Optional<Integer> limitMerchant, Optional<Integer> dayAgo) {

        // Get all transaction by Customer Id and apply limit if there is limit Merchant value
        List<TransactionDTO> transactions = limitMerchant.map(n -> transactionPersistencePort
                .findTransactionsByCustomerId(customerId)
                .stream()
                .limit(n)
                .collect(Collectors.toList())).orElseGet(() -> transactionPersistencePort.findTransactionsByCustomerId(customerId));

        return summaryCardSpendByMerchant(transactions);
    }

    private List<CardSpendMerchant> summaryCardSpendByMerchant(List<TransactionDTO> transactions) {
        // Group by Merchant and sum
        Map<Long, Double> sumByMerchantID = transactions.stream().collect(Collectors.groupingBy(
                TransactionDTO::getMerchantId,
                Collectors.summingDouble(TransactionDTO::getAmountCents))
        );

        return sumByMerchantID.entrySet().stream()
                .sorted()
                .map(m -> CardSpendMerchant.builder()
                        .merchant(m.getKey())
                        .amount(m.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
