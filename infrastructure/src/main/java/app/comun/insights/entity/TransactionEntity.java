package app.comun.insights.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    private String id;
    private Long customerId;
    private Long merchantId;
    private Double amountCents;
    private Date date;
}
