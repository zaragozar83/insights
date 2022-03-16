package app.comun.insights.config;

import app.comun.insights.adapter.TransactionJPAAdapter;
import app.comun.insights.port.infrastructure.TransactionPersistencePort;
import app.comun.insights.port.service.TransactionServiceImpl;
import app.comun.insights.port.userinterface.TransactionServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsightsConfig {

    @Bean
    public TransactionPersistencePort transactionPersistence() {
        return new TransactionJPAAdapter();
    }

    @Bean
    public TransactionServicePort transactionService() {
        return new TransactionServiceImpl(transactionPersistence());
    }


}
