package tech.financial.cloud.codingexercise.domain.impl;

import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.domain.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import java.util.*;

public class InMemoryPaymentResourceRepository implements Repository<PaymentResource> {

    private Map<UUID, PaymentResource> storage = new HashMap<>();

    @Override
    public PaymentResource update(UUID id, PaymentResource resource) {
        if (!storage.containsKey(id)) {
            throw new ResourceNotFoundException();
        }
        return storage.put(id, resource);
    }

    @Override
    public PaymentResource save(PaymentResource t) {
        return storage.put(t.getId(), t);
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
