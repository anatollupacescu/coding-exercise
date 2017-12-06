package tech.financial.cloud.codingexercise.data.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.data.entity.AttributesMap;
import tech.financial.cloud.codingexercise.data.entity.PaymentResource;
import tech.financial.cloud.codingexercise.domain.model.Type;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentResourceRepositoryTest {

    @Autowired
    private PaymentResourceRepository repository;

    @Test
    public void canSave() {
        PaymentResource resource = createPaymentResourceEntity();
        repository.save(resource);
        Iterable<PaymentResource> one = repository.findAll();
        assertThat(one).isNotEmpty();
        PaymentResource next = one.iterator().next();
        resource.setId(next.getId());
        assertThat(next).isEqualTo(resource);
    }

    private PaymentResource createPaymentResourceEntity() {
        PaymentResource entity = new PaymentResource();
        entity.setOrganisation_id(UUID.randomUUID());
        AttributesMap attributes = new AttributesMap();
        attributes.setAmount(BigDecimal.valueOf(100.21));
        entity.setAttributes(attributes);
        entity.setType(Type.Payment);
        return entity;
    }
}