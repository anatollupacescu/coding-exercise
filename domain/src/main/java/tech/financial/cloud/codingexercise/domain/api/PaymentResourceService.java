package tech.financial.cloud.codingexercise.domain.api;

import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import java.util.List;
import java.util.UUID;

public interface PaymentResourceService {

  List<PaymentResource> getAll();

  PaymentResource getById(UUID id);

  PaymentResource create(PaymentResource resource);

  void delete(UUID resourceId);

  void update(PaymentResource resource);
}
