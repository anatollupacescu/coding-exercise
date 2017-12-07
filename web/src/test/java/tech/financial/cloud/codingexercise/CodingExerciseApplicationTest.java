package tech.financial.cloud.codingexercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.data.service.TestUtils;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;
import tech.financial.cloud.codingexercise.mapper.ModelToEntityMapper;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodingExerciseApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllPayments() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/v1/payments", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void savePaymentWithCorrectPersistsData() {
        TestUtils testUtils = new TestUtils();
        PaymentResourceEntity paymentResourceEntity = testUtils.createPaymentResourceEntity();
        PaymentResource payment = ModelToEntityMapper.INSTANCE.fromEntity(paymentResourceEntity);
        payment.setId(UUID.randomUUID());
        ResponseEntity<PaymentResource> entity =
                restTemplate.postForEntity("/v1/payments", payment, PaymentResource.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getBody()).isNotNull();
        PaymentResource receivedEntity = entity.getBody();
        UUID id = receivedEntity.getId();
        payment.setId(id);
        List<String> receivedLocations = entity.getHeaders().get("Location");
        assertThat(receivedLocations).isNotNull();
        assertThat(receivedLocations.isEmpty()).isFalse();
        String location = receivedLocations.iterator().next();
        assertThat(location).endsWith("/v1/payments/".concat(id.toString()));
        assertThat(receivedEntity).isEqualTo(payment);
    }

    @Test
    public void savePaymentWithMissingMandatoryFieldReturnBadRequest() {
        TestUtils testUtils = new TestUtils();
        PaymentResourceEntity paymentResourceEntity = testUtils.createPaymentResourceEntity();
        PaymentResource payment = ModelToEntityMapper.INSTANCE.fromEntity(paymentResourceEntity);
        payment.setType(null);
        ResponseEntity<PaymentResource> entity = restTemplate.postForEntity("/v1/payments", payment, PaymentResource.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
