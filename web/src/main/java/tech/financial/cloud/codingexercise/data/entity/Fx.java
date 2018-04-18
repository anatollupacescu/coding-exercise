package tech.financial.cloud.codingexercise.data.entity;

import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Fx {
    private String contract_reference;
    private String exchange_rate;
    private BigDecimal original_amount;
    private Currency original_currency;
}
