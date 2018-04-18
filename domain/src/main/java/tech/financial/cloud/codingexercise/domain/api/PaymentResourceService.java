package tech.financial.cloud.codingexercise.domain.api;

import java.util.List;
import java.util.UUID;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

public interface PaymentResourceService {

    List<PaymentResource> getAll();

    PaymentResource getById(UUID id);

    PaymentResource create(PaymentResource resource);

    void delete(UUID resourceId);

    void update(UUID uuid, PaymentResource resource);
}
