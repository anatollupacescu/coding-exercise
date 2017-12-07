package tech.financial.cloud.codingexercise.data.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentResourceEntityRepositoryTest {

    @Autowired
    private PaymentResourceEntityRepository repository;

    @Test
    @Transactional
    public void canSave() {
        TestUtils utils = new TestUtils();
        PaymentResourceEntity resource = utils.createPaymentResourceEntity();
        repository.save(resource);
        Iterable<PaymentResourceEntity> all = repository.findAll();
        assertThat(all).isNotEmpty();
        PaymentResourceEntity next = all.iterator().next();
        assertThat(next).isNotNull();
        resource.setId(next.getId());
        assertThat(next).isEqualTo(resource);
    }
}
