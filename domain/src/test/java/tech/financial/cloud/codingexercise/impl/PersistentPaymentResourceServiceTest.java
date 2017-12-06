package tech.financial.cloud.codingexercise.impl;

import org.junit.Before;
import org.junit.Test;
import tech.financial.cloud.codingexercise.api.PaymentResourceRepository;
import tech.financial.cloud.codingexercise.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.model.PaymentResource;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PersistentPaymentResourceServiceTest {

  private PaymentResourceService paymentResourceService;

  @Before
  public void setUp() {
    PaymentResourceRepository resourceRepository = new InMemoryPaymentResourceRepository();
    paymentResourceService = new PersistentPaymentResourceService(resourceRepository);
  }

  @Test
  public void canCreatePaymentResource() throws Exception {
    UUID uuid = UUID.randomUUID();
    PaymentResource paymentResource = createPaymentResource(uuid);
    paymentResourceService.create(paymentResource);
    assertThat(paymentResourceService.getById(uuid), is(equalTo(paymentResource)));
    assertThat(
        paymentResourceService.getAll(), is(equalTo(Collections.singletonList(paymentResource))));
  }

  @Test(expected = NullPointerException.class)
  public void canNotCreatePaymentResourceWithoutId() throws Exception {
    PaymentResource paymentResource = new PaymentResource();
    paymentResourceService.create(paymentResource);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void canNotDeleteMissingPaymentResource() throws Exception {
    paymentResourceService.delete(UUID.randomUUID());
  }

  @Test(expected = NullPointerException.class)
  public void callingDeleteWithNullArgumentThrowsNPE() throws Exception {
    paymentResourceService.delete(null);
  }

  @Test
  public void deletedResourceNoLongerAvailable() {
    UUID uuid = UUID.randomUUID();
    PaymentResource paymentResource = createPaymentResource(uuid);
    paymentResourceService.create(paymentResource);
    paymentResourceService.delete(uuid);
    assertThat(paymentResourceService.getById(uuid), is(nullValue()));
    assertThat(paymentResourceService.getAll().isEmpty(), is(equalTo(true)));
  }

  @Test(expected = NullPointerException.class)
  public void canNotUpdatePaymentResourceWithoutId() throws Exception {
    PaymentResource paymentResource = new PaymentResource();
    paymentResourceService.update(paymentResource);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void canNotUpdateMissingResource() throws Exception {
    paymentResourceService.update(createPaymentResource(UUID.randomUUID()));
  }

  @Test(expected = NullPointerException.class)
  public void callingUpdateWithNullArgumentThrowsNPE() {
    paymentResourceService.update(null);
  }

  @Test
  public void updateResourcePaymentIsPersisted() {
    UUID uuid = UUID.randomUUID();
    PaymentResource paymentResource = createPaymentResource(uuid);
    paymentResourceService.create(paymentResource);
    paymentResource = createPaymentResource(uuid);
    paymentResource.setVersion(2);
    paymentResourceService.update(paymentResource);
    PaymentResource byId = paymentResourceService.getById(uuid);
    assertThat(byId, is(notNullValue()));
    assertThat(byId.getVersion(), is(equalTo(2)));
  }

  private PaymentResource createPaymentResource(UUID uuid) {
    PaymentResource paymentResource = new PaymentResource();
    paymentResource.setId(uuid);
    return paymentResource;
  }
}
