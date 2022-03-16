package app.comun.insights.port.service;

import app.comun.insights.domain.CardSpendMerchant;
import app.comun.insights.domain.TransactionDTO;
import app.comun.insights.port.infrastructure.TransactionPersistencePort;
import app.comun.insights.port.userinterface.TransactionServicePort;

import java.util.Comparator;
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

        // Get all transaction by Customer Id
        List<TransactionDTO> transactionDTOList = transactionPersistencePort.findTransactionsByCustomerId(customerId);

        List<CardSpendMerchant> spendMerchantList = summaryCardSpendByMerchant(transactionDTOList);

        // and apply limit if there is limit Merchant value
        return limitMerchant.map(n -> spendMerchantList
                        .stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(n)
                        .collect(Collectors.toList())
                ).orElseGet(
                        () -> spendMerchantList
                                .stream()
                                .sorted(Comparator.reverseOrder())
                                .collect(Collectors.toList())
        );
    }

    private List<CardSpendMerchant> summaryCardSpendByMerchant(List<TransactionDTO> transactions) {

        // Group by Merchant and sum
        Map<Long, Double> sumByMerchantID = transactions.stream().collect(Collectors.groupingBy(
                TransactionDTO::getMerchantId,
                Collectors.summingDouble(TransactionDTO::getAmountCents))
        );

        return sumByMerchantID.entrySet().stream()
                .map(m -> CardSpendMerchant.builder()
                        .merchant(m.getKey())
                        .amount(m.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
