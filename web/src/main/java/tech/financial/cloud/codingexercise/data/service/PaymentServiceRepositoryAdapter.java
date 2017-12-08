package tech.financial.cloud.codingexercise.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;
import tech.financial.cloud.codingexercise.mapper.PaymentResourceMapper;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
public class PaymentServiceRepositoryAdapter implements Repository<PaymentResource> {

    @Autowired
    private PaymentResourceEntityRepository repository;

    @Override
    public PaymentResource update(UUID id, PaymentResource resource) {
        ensureExistence(id);
        resource.setId(id);
        return save(resource);
    }

    @Override
    public PaymentResource save(PaymentResource paymentResource) {
        PaymentResourceEntity entity = toEntity(paymentResource);
        PaymentResourceEntity persistedEntity = repository.save(entity);
        return fromEntity(persistedEntity);
    }

    @Override
    public void remove(UUID id) {
        ensureExistence(id);
        repository.delete(id);
    }

    @Override
    public PaymentResource get(UUID id) {
        ensureExistence(id);
        return fromEntity(repository.findOne(id));
    }

    @Override
    public List<PaymentResource> list() {
        List<PaymentResource> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(e -> list.add(fromEntity(e)));
        return list;
    }

    private void ensureExistence(UUID id) {
        if (!repository.exists(id)) {
            throw new EntityNotFoundException(id.toString());
        }
    }

    private PaymentResource fromEntity(PaymentResourceEntity entity) {
        return PaymentResourceMapper.INSTANCE.fromEntity(entity);
    }

    private PaymentResourceEntity toEntity(PaymentResource paymentResource) {
        return PaymentResourceMapper.INSTANCE.toEntity(paymentResource);
    }
}
