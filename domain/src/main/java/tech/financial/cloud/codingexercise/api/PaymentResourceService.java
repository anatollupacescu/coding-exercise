package tech.financial.cloud.codingexercise.api;

import tech.financial.cloud.codingexercise.model.PaymentResource;

import java.util.List;
import java.util.UUID;

public interface PaymentResourceService {

  List<PaymentResource> getAll();

  PaymentResource getById(UUID id);

  void create(PaymentResource resource);

  void delete(UUID resourceId);

  void update(PaymentResource resource);
}
