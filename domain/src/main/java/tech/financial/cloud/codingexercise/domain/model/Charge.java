package tech.financial.cloud.codingexercise.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Charge {
    private String amount;
    private Currency currency;
  /*
  {
      "amount": "10.00",
      "currency": "USD"
  }
   */
}
