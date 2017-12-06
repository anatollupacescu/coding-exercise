package tech.financial.cloud.codingexercise;

import lombok.Data;
import tech.financial.cloud.codingexercise.model.PaymentResource;

import java.util.List;

@Data
public class ApiResponse {

  private List<PaymentResource> data;
  private Links links;

  @Data
  public static class Links {
    private String self;
  }
}
