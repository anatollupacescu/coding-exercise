package tech.financial.cloud.codingexercise.domain.model;

import lombok.Data;

@Data
public class Party {
    private String account_name;
    private String account_number;
    private AccountNumberCode account_number_code;
    private int account_type;
    private String address;
    private long bank_id;
    private String bank_id_code;
    private String name;
  /*
  "beneficiary_party": {
  "account_name": "W Owens",
  "account_number": "GB29XABC10161234567801",
  "account_number_code": "BBAN",
  "account_type": 0,
  "address": "1 The Beneficiary Localtown SE2",
  "bank_id": "403000",
  "bank_id_code": "GBDSC",
  "name": "Wilfred Jeremiah Owens"
   */
}
