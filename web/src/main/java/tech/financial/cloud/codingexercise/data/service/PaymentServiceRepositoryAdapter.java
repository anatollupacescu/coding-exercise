package tech.financial.cloud.codingexercise.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.data.entity.PaymentResource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentServiceRepositoryAdapter implements Repository<PaymentResource> {

    @Autowired
    private PaymentResourceRepository repository;

    @Override
    public void save(PaymentResource paymentResource) {
        repository.save(paymentResource);
    }

    @Override
    public void remove(UUID id) {
        repository.delete(id);
    }

    @Override
    public PaymentResource get(UUID id) {
        return repository.findOne(id);
    }

    @Override
    public List<PaymentResource> list() {
        List<PaymentResource> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
