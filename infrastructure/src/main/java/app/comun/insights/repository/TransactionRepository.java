package app.comun.insights.repository;

import app.comun.insights.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

    List<TransactionEntity> findTransactionEntitiesByCustomerId(Long customerId);
}
