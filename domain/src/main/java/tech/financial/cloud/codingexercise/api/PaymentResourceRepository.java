package tech.financial.cloud.codingexercise.api;

import tech.financial.cloud.codingexercise.model.PaymentResource;

import java.util.List;
import java.util.UUID;

public interface PaymentResourceRepository {

  void save(PaymentResource t);

  void remove(UUID id);

  PaymentResource get(UUID id);

  List<PaymentResource> list();
}
