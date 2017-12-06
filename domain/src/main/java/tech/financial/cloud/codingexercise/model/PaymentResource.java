package tech.financial.cloud.codingexercise.model;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResource {
  private Type type;
  private UUID id;
  private int version;
  private UUID organisation_id;
  private AttributesMap attributes;
}
