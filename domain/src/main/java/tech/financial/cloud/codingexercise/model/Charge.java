package tech.financial.cloud.codingexercise.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Charge {
  private BigDecimal amount;
  private Currency currency;
  /*
  {
      "amount": "10.00",
      "currency": "USD"
  }
   */
}
