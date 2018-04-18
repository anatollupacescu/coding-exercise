package tech.financial.cloud.codingexercise.data.service;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;

@Component
public interface PaymentResourceEntityRepository extends CrudRepository<PaymentResourceEntity, UUID> {
}
