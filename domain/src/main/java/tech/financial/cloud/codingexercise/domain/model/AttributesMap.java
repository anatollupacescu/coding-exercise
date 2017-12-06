package tech.financial.cloud.codingexercise.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Data
public class AttributesMap {
  private BigDecimal amount;
  private Party beneficiary_party;
  private ChargesInformation charges_information;
  private Currency currency;
  private Party debtor_party;
  private String end_to_end_reference;
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
  private Party sponsor_party;
}
