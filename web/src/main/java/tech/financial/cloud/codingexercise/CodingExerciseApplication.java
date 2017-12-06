package tech.financial.cloud.codingexercise;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.financial.cloud.codingexercise.data.service.PaymentServiceRepositoryAdapter;
import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.impl.PersistentPaymentResourceService;
import tech.financial.cloud.codingexercise.rest.PaymentResourceController;

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

  @Bean
  public PaymentResourceController paymentResourceController(PaymentResourceService service) {
      return new PaymentResourceController(service);
  }

  @Bean
  public ResourceConfig jerseyConfig() {
    return new JerseyConfig();
  }

  class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
      register(PaymentResourceController.class);
    }
  }
}
