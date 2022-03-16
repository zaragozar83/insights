package app.comun.insights.controller;

import app.comun.insights.domain.CardSpendMerchant;
import app.comun.insights.domain.TransactionDTO;
import app.comun.insights.port.userinterface.TransactionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionServicePort transactionServicePort;

    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO){

        return ResponseEntity.ok(transactionServicePort.saveTransaction(transactionDTO));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        return ResponseEntity.ok(transactionServicePort.getTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionServicePort.getTransactionById(id));
    }

    @GetMapping("/insights/{customerId}")
    public ResponseEntity<List<CardSpendMerchant>> getInsightsByCustomerId(@PathVariable Long customerId,
                                                                           @RequestParam Optional<Integer> limitMerchant,
                                                                           @RequestParam Optional<Integer> dayAgo){
        return ResponseEntity.ok(transactionServicePort.findTopCardSpendCategoriesByCustomerId(customerId, limitMerchant, dayAgo));
    }
}
