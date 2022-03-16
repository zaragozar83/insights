package app.comun.insights.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardSpendMerchant implements Comparable<CardSpendMerchant>{

    private Long merchant;
    private Double amount;

    @Override
    public int compareTo(CardSpendMerchant o) {

        if (amount == null)
            return 0;
        return amount.compareTo(o.amount);
    }
}
