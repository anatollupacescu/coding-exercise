package tech.financial.cloud.codingexercise.domain.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import tech.financial.cloud.codingexercise.domain.api.Repository;
import tech.financial.cloud.codingexercise.domain.api.ResourceNotFoundException;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

public class InMemoryPaymentResourceRepositoryTest {

    private Repository<PaymentResource> resourceRepository;

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

    @Test(expected = ResourceNotFoundException.class)
    public void canNotRemoveNonExistentResource() {
        UUID uuid = UUID.randomUUID();
        resourceRepository.remove(uuid);
    }

    @Test
    public void presentResourceCanBeRemoved() {
        UUID uuid = UUID.randomUUID();
        PaymentResource paymentResource = createPaymentResource(uuid);
        paymentResource = resourceRepository.save(paymentResource);
        uuid = paymentResource.getId();
        resourceRepository.remove(uuid);
        assertThat(resourceRepository.list().isEmpty(), is(equalTo(true)));
    }

    private PaymentResource createPaymentResource(UUID uuid) {
        PaymentResource paymentResource = new PaymentResource();
        paymentResource.setId(uuid);
        return paymentResource;
    }
}
