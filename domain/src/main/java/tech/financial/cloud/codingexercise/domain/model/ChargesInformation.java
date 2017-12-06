package tech.financial.cloud.codingexercise.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Data
public class ChargesInformation {
  private String bearer_code;
  private List<Charge> sender_charges;
  private BigDecimal receiver_charges_amount;
  private Currency receiver_charges_currency;
  /*
  "bearer_code": "SHAR",
  "sender_charges": [
      {
          "amount": "5.00",
          "currency": "GBP"
      },

  ],
  "receiver_charges_amount": "1.00",
  "receiver_charges_currency": "USD"
   */
}
