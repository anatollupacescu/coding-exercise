package tech.financial.cloud.codingexercise.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Embeddable
@Data
public class ChargesInformation {

    @Column(nullable = false)
    private String bearer_code;

    @Column(nullable = false)
    @ElementCollection
    private List<Charge> sender_charges;

    @Column(nullable = false)
    private BigDecimal receiver_charges_amount;

    @Column(nullable = false)
    private Currency receiver_charges_currency;
}
