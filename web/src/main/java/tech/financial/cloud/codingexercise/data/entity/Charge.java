package tech.financial.cloud.codingexercise.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
@Data
public class Charge {
    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private Currency currency;
}
