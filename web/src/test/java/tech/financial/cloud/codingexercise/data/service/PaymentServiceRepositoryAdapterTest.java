package tech.financial.cloud.codingexercise.data.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;
import tech.financial.cloud.codingexercise.mapper.ModelToEntityMapper;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PaymentServiceRepositoryAdapterTest {

    private final TestUtils util = new TestUtils();

    @Mock
    private PaymentResourceEntityRepository repository;

    @InjectMocks
    private PaymentServiceRepositoryAdapter adapter;

    @Test
    public void savePersistsCorrectlyMappedEntity() throws Exception {
        PaymentResourceEntity paymentResourceEntity = util.createPaymentResourceEntity();
        PaymentResource model = createPaymentResourceModel(paymentResourceEntity);
        adapter.save(model);
        verify(repository).save(paymentResourceEntity);
    }

    @Test
    public void removeDeletesCorrectId() throws Exception {
        UUID id = UUID.randomUUID();
        when(repository.exists(id)).thenReturn(true);
        adapter.remove(id);
        verify(repository).delete(id);
    }

    @Test
    public void getReturnsCorrectlyMappedModel() throws Exception {
        UUID id = UUID.randomUUID();
        when(repository.exists(id)).thenReturn(true);
        PaymentResourceEntity paymentResourceEntity = util.createPaymentResourceEntity();
        when(repository.findOne(id)).thenReturn(paymentResourceEntity);
        PaymentResource model = adapter.get(id);
        assertThat(model, is(equalTo(createPaymentResourceModel(paymentResourceEntity))));
    }

    @Test
    public void findAllReturnsCorrectlyMappedCollection() {
        PaymentResourceEntity paymentResourceEntity = util.createPaymentResourceEntity();
        when(repository.findAll()).thenReturn(Collections.singletonList(paymentResourceEntity));
        List<PaymentResource> all = adapter.list();
        assertThat(all, is(notNullValue()));
        assertThat(all.isEmpty(), is(equalTo(false)));
        PaymentResource model = all.iterator().next();
        assertThat(model, is(equalTo(createPaymentResourceModel(paymentResourceEntity))));
    }

    private PaymentResource createPaymentResourceModel(PaymentResourceEntity paymentResourceEntity) {
        return ModelToEntityMapper.INSTANCE.fromEntity(paymentResourceEntity);
    }
}
