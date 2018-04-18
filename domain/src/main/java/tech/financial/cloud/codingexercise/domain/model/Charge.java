package tech.financial.cloud.codingexercise.domain.model;

import java.util.Currency;
import lombok.Data;

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
