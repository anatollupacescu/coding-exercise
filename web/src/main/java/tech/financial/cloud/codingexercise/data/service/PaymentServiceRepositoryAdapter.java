package tech.financial.cloud.codingexercise.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;
import tech.financial.cloud.codingexercise.mapper.ModelToEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentServiceRepositoryAdapter implements Repository<PaymentResource> {

    @Autowired
    private PaymentResourceEntityRepository repository;

    @Override
    public void save(PaymentResource paymentResource) {
        PaymentResourceEntity entity = toEntity(paymentResource);
        repository.save(entity);
    }

    @Override
    public void remove(UUID id) {
        repository.delete(id);
    }

    @Override
    public PaymentResource get(UUID id) {
        return fromEntity(repository.findOne(id));
    }

    @Override
    public List<PaymentResource> list() {
        List<PaymentResource> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(e -> list.add(fromEntity(e)));
        return list;
    }

    private PaymentResource fromEntity(PaymentResourceEntity entity) {
        return ModelToEntityMapper.INSTANCE.fromEntity(entity);
    }

    private PaymentResourceEntity toEntity(PaymentResource paymentResource) {
        return ModelToEntityMapper.INSTANCE.toEntity(paymentResource);
    }
}
