package tech.financial.cloud.codingexercise.impl;

import org.junit.Before;
import org.junit.Test;
import tech.financial.cloud.codingexercise.api.PaymentResourceRepository;
import tech.financial.cloud.codingexercise.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.model.PaymentResource;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class InMemoryPaymentResourceRepositoryTest {

  private PaymentResourceRepository resourceRepository;

  @Before
  public void setUp() {
    resourceRepository = new InMemoryPaymentResourceRepository();
  }

  @Test
  public void repositoryIsCreatedEmpty() {
    List<PaymentResource> list = resourceRepository.list();
    assertThat(list, is(notNullValue()));
    assertThat(list.isEmpty(), is(equalTo(true)));
  }

  @Test
  public void savedPaymentResourceCanBeRetrieved() {
    UUID uuid = UUID.randomUUID();
    resourceRepository.save(createPaymentResource(uuid));
    List<PaymentResource> list = resourceRepository.list();
    assertThat(list, is(notNullValue()));
    assertThat(list.size(), is(equalTo(1)));
  }

  @Test
  public void canSaveMultipleResources() {
    UUID uuid = UUID.randomUUID();
    resourceRepository.save(createPaymentResource(uuid));
    uuid = UUID.randomUUID();
    resourceRepository.save(createPaymentResource(uuid));
    List<PaymentResource> list = resourceRepository.list();
    assertThat(list.size(), is(equalTo(2)));
  }

  @Test
  public void onlyLatestSavedResourceIsPersisted() {
    UUID uuid = UUID.randomUUID();
    PaymentResource paymentResource = createPaymentResource(uuid);
    resourceRepository.save(paymentResource);
    paymentResource = createPaymentResource(uuid);
    paymentResource.setVersion(2);
    resourceRepository.save(paymentResource);
    List<PaymentResource> list = resourceRepository.list();
    assertThat(list.size(), is(equalTo(1)));
    assertThat(list.iterator().next(), is(equalTo(paymentResource)));
  }

  private PaymentResource createPaymentResource(UUID uuid) {
    PaymentResource paymentResource = new PaymentResource();
    paymentResource.setId(uuid);
    return paymentResource;
  }

  @Test(expected = ResourceNotFoundException.class)
  public void canNotRemoveNonExistentResource() {
    UUID uuid = UUID.randomUUID();
    resourceRepository.remove(uuid);
  }

  @Test
  public void presentResourceCanBeRemoved() {
    UUID uuid = UUID.randomUUID();
    PaymentResource paymentResource = createPaymentResource(uuid);
    resourceRepository.save(paymentResource);
    resourceRepository.remove(uuid);
    assertThat(resourceRepository.list().isEmpty(), is(equalTo(true)));
  }
}
