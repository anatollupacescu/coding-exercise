package tech.financial.cloud.codingexercise.data.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ChargesInformation {

    @Column(nullable = false)
    private String bearer_code;

    @Column(nullable = false)
    @ElementCollection
    private List<Charge> sender_charges = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal receiver_charges_amount;

    @Column(nullable = false)
    private Currency receiver_charges_currency;
}
