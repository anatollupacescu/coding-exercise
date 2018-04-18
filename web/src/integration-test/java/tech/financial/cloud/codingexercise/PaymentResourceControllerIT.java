package tech.financial.cloud.codingexercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.data.entity.Charge;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.data.service.TestUtils;
import tech.financial.cloud.codingexercise.domain.model.AttributesMap;
import tech.financial.cloud.codingexercise.domain.model.ChargesInformation;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;
import tech.financial.cloud.codingexercise.mapper.PaymentResourceMapper;
import tech.financial.cloud.codingexercise.rest.ApiResponse;
import tech.financial.cloud.codingexercise.rest.ErrorMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentResourceControllerIT {

    private final String PAYMENTS_URL = "/v1/payments/";

    @Autowired
    private TestRestTemplate restTemplate;

    private final TestUtils testUtils = new TestUtils();

    @Test
    public void getAllPaymentsReturnsWellConstructedApiResponse() {
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(PAYMENTS_URL, ApiResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ApiResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getLinks()).isNotNull();
        ApiResponse.Links links = body.getLinks();
        assertThat(links.getSelf()).endsWith(PAYMENTS_URL);
        List<PaymentResource> data = body.getData();
        assertThat(data).isNotNull();
    }

    @Test
    public void saveCorrectPaymentPersistsData() {
        PaymentResource initialPaymentResource = createPaymentResource();
        ResponseEntity<ApiResponse> savedPayment = saveNewPayment(initialPaymentResource);
        assertThat(savedPayment.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ApiResponse apiResponse = savedPayment.getBody();
        assertThat(apiResponse).isNotNull();
        List<PaymentResource> data = apiResponse.getData();
        assertThat(data).isNotNull();
        assertThat(data.isEmpty()).isFalse();
        PaymentResource savedPaymentResource = data.iterator().next();
        //everything was persisted
        UUID id = savedPaymentResource.getId();
        initialPaymentResource.setId(id);
        assertThat(savedPaymentResource).isEqualTo(initialPaymentResource);
        //the 'location' header points to the newly created resource
        List<String> receivedLocations = savedPayment.getHeaders().get("Location");
        assertThat(receivedLocations).isNotNull();
        assertThat(receivedLocations.isEmpty()).isFalse();
        String location = receivedLocations.iterator().next();
        assertThat(location).endsWith(PAYMENTS_URL.concat(id.toString()));
    }

    @Test
    public void retrievingNonExistentPaymentReturnsNotFound() {
        ResponseEntity<ErrorMessage> response = restTemplate.getForEntity(PAYMENTS_URL.concat(UUID.randomUUID().toString()), ErrorMessage.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(404);
    }

    @Test
    public void canRetrieveSinglePaymentByCorrectId() {
        PaymentResource payment = createPaymentResource();
        ResponseEntity<ApiResponse> savedResource = saveNewPayment(payment);
        PaymentResource resource = extractSingleResource(savedResource);
        UUID id = resource.getId();
        ResponseEntity<ApiResponse> retrievedResource = getById(id);
        ApiResponse body = retrievedResource.getBody();
        assertThat(body).isNotNull();
        String expectedSelf = ApiResponse.newLinks(PAYMENTS_URL.concat(id.toString())).getSelf();
        assertThat(body.getLinks().getSelf()).endsWith(expectedSelf);
        List<PaymentResource> data = body.getData();
        assertThat(data).isNotNull();
        payment.setId(id);
        assertThat(data).isEqualTo(Collections.singletonList(payment));
    }

    @Test
    public void savePaymentWithMissingMandatoryFieldReturnBadRequest() {
        PaymentResourceEntity paymentResourceEntity = testUtils.createPaymentResourceEntity();
        PaymentResource payment = PaymentResourceMapper.INSTANCE.fromEntity(paymentResourceEntity);
        payment.setType(null);
        ResponseEntity<ApiResponse> response = saveNewPayment(payment);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteNonExistentPaymentReturnsBadRequest() {
        ResponseEntity<String> response = deleteById(UUID.randomUUID());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deletingExistingPaymentRemovesEntity() {
        PaymentResource payment = createPaymentResource();
        ResponseEntity<ApiResponse> savedPayment = saveNewPayment(payment);
        PaymentResource resource = extractSingleResource(savedPayment);
        UUID uuid = resource.getId();
        ResponseEntity<String> response = deleteById(uuid);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        ResponseEntity<ApiResponse> deletedResource = getById(uuid);
        assertThat(deletedResource.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void updateNonExistentPaymentReturnsNotFound() {
        PaymentResource payment = createPaymentResource();
        HttpEntity<PaymentResource> body = new HttpEntity<>(payment);
        ResponseEntity<ErrorMessage> response = restTemplate.exchange(PAYMENTS_URL.concat(UUID.randomUUID().toString()), HttpMethod.PUT, body, ErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(404);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void updatingCorrectPaymentPersistsChange() {
        PaymentResourceEntity paymentResourceEntity = testUtils.createPaymentResourceEntity();
        PaymentResource payment = PaymentResourceMapper.INSTANCE.fromEntity(paymentResourceEntity);
        //creating payment
        ResponseEntity<ApiResponse> savePaymentResponse = saveNewPayment(payment);
        PaymentResource resource = extractSingleResource(savePaymentResponse);
        UUID id = resource.getId();
        //adding changes
        Charge newCharge = newCharge();
        List<Charge> updatedSenderCharges = Collections.singletonList(newCharge);
        paymentResourceEntity.getAttributes().getCharges_information().setSender_charges(updatedSenderCharges);
        payment = PaymentResourceMapper.INSTANCE.fromEntity(paymentResourceEntity);
        HttpEntity<PaymentResource> updatedPaymentResource = new HttpEntity<>(payment);
        //updating payment
        ResponseEntity<String> updateResponse = restTemplate.exchange(PAYMENTS_URL.concat(id.toString()), HttpMethod.PUT, updatedPaymentResource, String.class);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getBody()).isNull();
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        //check if payment resource has been updated
        ResponseEntity<ApiResponse> updatedPaymentResponseEntity = getById(id);
        assertThat(updatedPaymentResponseEntity).isNotNull();
        ApiResponse body = updatedPaymentResponseEntity.getBody();
        assertThat(body).isNotNull();
        List<PaymentResource> data = body.getData();
        assertThat(data).isNotNull();
        assertThat(data.isEmpty()).isFalse();
        PaymentResource next = data.iterator().next();
        assertThat(next).isNotNull();
        AttributesMap attributes = next.getAttributes();
        assertThat(attributes).isNotNull();
        ChargesInformation charges_information = attributes.getCharges_information();
        assertThat(charges_information).isNotNull();
        List<tech.financial.cloud.codingexercise.domain.model.Charge> sender_charges = charges_information.getSender_charges();
        assertThat(sender_charges).isNotNull();
        assertThat(sender_charges.size()).isEqualTo(1);
        tech.financial.cloud.codingexercise.domain.model.Charge updatedCharge = sender_charges.iterator().next();
        assertThat(updatedCharge.getAmount()).isEqualTo(newCharge.getAmount());
        assertThat(updatedCharge.getCurrency()).isEqualTo(newCharge.getCurrency());
    }

    private ResponseEntity<String> deleteById(UUID uuid) {
        String url = PAYMENTS_URL.concat(uuid.toString());
        return restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
    }

    private ResponseEntity<ApiResponse> getById(UUID id) {
        return restTemplate.getForEntity(PAYMENTS_URL.concat(id.toString()), ApiResponse.class);
    }

    private ResponseEntity<ApiResponse> saveNewPayment(PaymentResource payment) {
        return restTemplate.postForEntity(PAYMENTS_URL, payment, ApiResponse.class);
    }

    private PaymentResource createPaymentResource() {
        PaymentResourceEntity paymentResourceEntity = testUtils.createPaymentResourceEntity();
        return PaymentResourceMapper.INSTANCE.fromEntity(paymentResourceEntity);
    }

    private PaymentResource extractSingleResource(ResponseEntity<ApiResponse> apiResponseEntity) {
        ApiResponse apiResponse = apiResponseEntity.getBody();
        List<PaymentResource> data = apiResponse.getData();
        return data.iterator().next();
    }

    private Charge newCharge() {
        Charge charge = new Charge();
        charge.setCurrency(Currency.getInstance("RON"));
        charge.setAmount("99.34");
        return charge;
    }
}
