package app.comun.insights.adapter;

import app.comun.insights.domain.TransactionDTO;
import app.comun.insights.entity.TransactionEntity;
import app.comun.insights.mapper.TransactionMapper;
import app.comun.insights.port.infrastructure.TransactionPersistencePort;
import app.comun.insights.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionJPAAdapter implements TransactionPersistencePort {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {

        TransactionEntity transactionEntity = TransactionMapper.INSTANCE.transactionDTOToTransactionEntity(transactionDTO);
        transactionEntity.setDate(new Date());
        TransactionEntity transactionSaved = transactionRepository.save(transactionEntity);

        return TransactionMapper.INSTANCE.transactionEntityToTransactionDTO(transactionSaved);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return TransactionMapper.INSTANCE.transactionEntityListToTransactionDTOList(
                transactionRepository.findAll()
        );
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {

        Optional<TransactionEntity> maybeTransaction = transactionRepository.findById(transactionId);

        return maybeTransaction
                .map(TransactionMapper.INSTANCE::transactionEntityToTransactionDTO)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transactionId));
    }

    @Override
    public List<TransactionDTO> findTransactionsByCustomerId(Long customerId) {

        return TransactionMapper.INSTANCE.transactionEntityListToTransactionDTOList(transactionRepository.findTransactionEntitiesByCustomerId(customerId));

    }
}
