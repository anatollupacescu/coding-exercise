package tech.financial.cloud.codingexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.financial.cloud.codingexercise.data.service.PaymentServiceRepositoryAdapter;
import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.impl.PersistentPaymentResourceService;

@SpringBootApplication
public class CodingExerciseApplication {

  public static void main(String[] args) {
    SpringApplication.run(CodingExerciseApplication.class, args);
  }

  @Bean
  public PaymentServiceRepositoryAdapter paymentServiceSpringDataRepository() {
    return new PaymentServiceRepositoryAdapter();
  }

  @Bean
  public PaymentResourceService paymentResourceService() {
    return new PersistentPaymentResourceService(paymentServiceSpringDataRepository());
  }
}
