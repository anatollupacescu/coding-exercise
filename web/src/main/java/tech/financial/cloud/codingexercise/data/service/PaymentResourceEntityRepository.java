package tech.financial.cloud.codingexercise.data.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;

import java.util.UUID;

@Component
interface PaymentResourceEntityRepository extends CrudRepository<PaymentResourceEntity, UUID> {
}
