package tech.financial.cloud.codingexercise;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.data.service.PaymentResourceEntityRepository;
import tech.financial.cloud.codingexercise.data.service.TestUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentResourceEntityRepositoryIT {

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
