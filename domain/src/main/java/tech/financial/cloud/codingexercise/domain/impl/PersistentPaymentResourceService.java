package tech.financial.cloud.codingexercise.domain.impl;

import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.domain.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PersistentPaymentResourceService implements PaymentResourceService {

    private final Repository<PaymentResource> resourceRepository;

    public PersistentPaymentResourceService(Repository<PaymentResource> resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public List<PaymentResource> getAll() {
        return new ArrayList<>(resourceRepository.list());
    }

    @Override
    public PaymentResource getById(UUID id) {
        Objects.requireNonNull(id, "Missing resource identifier");
        return resourceRepository.get(id);
    }

    @Override
    public PaymentResource create(PaymentResource resource) {
        Objects.requireNonNull(resource, "Missing mandatory argument");
        Objects.requireNonNull(resource.getId(), "Missing resource identifier");
        return resourceRepository.save(resource);
    }

    @Override
    public void delete(UUID id) {
        Objects.requireNonNull(id, "Missing resource identifier");
        resourceRepository.remove(id);
    }

    @Override
    public void update(PaymentResource resource) {
        Objects.requireNonNull(resource, "Missing mandatory argument");
        UUID id = resource.getId();
        Objects.requireNonNull(id, "Missing resource identifier");
        if (resourceRepository.get(id) == null) {
            throw new ResourceNotFoundException();
        }
        resourceRepository.save(resource);
    }
}
