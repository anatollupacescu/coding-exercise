package tech.financial.cloud.codingexercise.impl;

import tech.financial.cloud.codingexercise.api.PaymentResourceRepository;
import tech.financial.cloud.codingexercise.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.model.PaymentResource;

import java.util.*;

public class InMemoryPaymentResourceRepository implements PaymentResourceRepository {

  private Map<UUID, PaymentResource> storage = new HashMap<>();

  @Override
  public void save(PaymentResource t) {
    storage.put(t.getId(), t);
  }

  @Override
  public void remove(UUID id) {
    if (null == storage.remove(id)) {
      throw new ResourceNotFoundException();
    }
  }

  @Override
  public PaymentResource get(UUID id) {
    return storage.get(id);
  }

  @Override
  public List<PaymentResource> list() {
    return new ArrayList<>(storage.values());
  }
}
