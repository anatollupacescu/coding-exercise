package tech.financial.cloud.codingexercise.data.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import tech.financial.cloud.codingexercise.data.entity.PaymentResource;

import java.util.UUID;

@Component
interface PaymentResourceRepository extends CrudRepository<PaymentResource, UUID> {
}
