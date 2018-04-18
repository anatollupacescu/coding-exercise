package tech.financial.cloud.codingexercise.domain.model;

import java.math.BigDecimal;
import java.util.Currency;
import lombok.Data;

@Data
public class Fx {
    private String contract_reference;
    private String exchange_rate;
    private BigDecimal original_amount;
    private Currency original_currency;
  /*
  "contract_reference": "FX123",
  "exchange_rate": "2.00000",
  "original_amount": "200.42",
  "original_currency": "USD"
  */
}
