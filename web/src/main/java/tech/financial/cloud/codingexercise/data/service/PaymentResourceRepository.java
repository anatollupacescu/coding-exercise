package tech.financial.cloud.codingexercise.data.service;

import org.springframework.data.repository.CrudRepository;
import tech.financial.cloud.codingexercise.data.entity.PaymentResource;

import java.util.UUID;

interface PaymentResourceRepository extends CrudRepository<PaymentResource, UUID> {
}
