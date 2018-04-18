package tech.financial.cloud.codingexercise.data.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class AttributesMap {
    private BigDecimal amount;
    @Embedded
    private Party beneficiary_party;
    @Embedded
    private ChargesInformation charges_information;
    private Currency currency;
    @Embedded
    private Party debtor_party;
    private String end_to_end_reference;
    @Embedded
    private Fx fx;
    private long numeric_reference;
    private long payment_id;
    private String payment_purpose;
    private String payment_scheme;
    private String payment_type;
    private Date processing_date;
    private String reference;
    private String scheme_payment_sub_type;
    private String scheme_payment_type;
    @Embedded
    private Party sponsor_party;
}
