package app.comun.insights.repository;

import app.comun.insights.entity.MerchantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends MongoRepository<MerchantEntity, String> {
}
