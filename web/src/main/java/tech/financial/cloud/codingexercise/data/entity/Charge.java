package tech.financial.cloud.codingexercise.data.entity;

import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Charge {
    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private Currency currency;
}
