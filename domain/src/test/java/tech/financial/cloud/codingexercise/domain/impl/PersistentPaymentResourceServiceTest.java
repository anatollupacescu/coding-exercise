package tech.financial.cloud.codingexercise.domain.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.domain.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

public class PersistentPaymentResourceServiceTest {

    private PaymentResourceService paymentResourceService;

    @Before
    public void setUp() {
        Repository<PaymentResource> resourceRepository = new InMemoryPaymentResourceRepository();
        paymentResourceService = new PersistentPaymentResourceService(resourceRepository);
    }

    @Test
    public void canCreatePaymentResource() {
        UUID uuid = UUID.randomUUID();
        PaymentResource paymentResource = createPaymentResource(uuid);
        paymentResourceService.create(paymentResource);
        assertThat(paymentResourceService.getById(uuid), is(not(equalTo(paymentResource))));
        assertThat(paymentResourceService.getAll(), is(equalTo(Collections.singletonList(paymentResource))));
    }

    @Test(expected = NullPointerException.class)
    public void canNotCreatePaymentResourceWithNullArgument() {
        paymentResourceService.create(null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void canNotDeleteMissingPaymentResource() {
        paymentResourceService.delete(UUID.randomUUID());
    }

    @Test(expected = NullPointerException.class)
    public void callingDeleteWithNullArgumentThrowsNPE() {
        paymentResourceService.delete(null);
    }

    @Test
    public void deletedResourceNoLongerAvailable() {
        UUID uuid = UUID.randomUUID();
        PaymentResource paymentResource = createPaymentResource(uuid);
        paymentResource = paymentResourceService.create(paymentResource);
        uuid = paymentResource.getId();
        paymentResourceService.delete(uuid);
        assertThat(paymentResourceService.getById(uuid), is(nullValue()));
        assertThat(paymentResourceService.getAll().isEmpty(), is(equalTo(true)));
    }

    @Test(expected = NullPointerException.class)
    public void canNotUpdatePaymentResourceWithoutId() {
        PaymentResource paymentResource = new PaymentResource();
        paymentResourceService.update(null, paymentResource);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void canNotUpdateMissingResource() {
        paymentResourceService.update(UUID.randomUUID(), createPaymentResource(UUID.randomUUID()));
    }

    @Test(expected = NullPointerException.class)
    public void callingUpdateWithNullArgumentThrowsNPE() {
        paymentResourceService.update(UUID.randomUUID(), null);
    }

    @Test
    public void updateResourcePaymentIsPersisted() {
        UUID uuid = UUID.randomUUID();
        PaymentResource paymentResource = createPaymentResource(uuid);
        paymentResource = paymentResourceService.create(paymentResource);
        paymentResource.setVersion(2);
        uuid = paymentResource.getId();
        paymentResourceService.update(uuid, paymentResource);
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
