package app.comun.insights.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private Long customerId;
    private Long merchantId;
    private Double amountCents;
    private Date date;
}
