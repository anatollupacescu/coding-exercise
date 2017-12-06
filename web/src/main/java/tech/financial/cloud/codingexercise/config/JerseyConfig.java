package tech.financial.cloud.codingexercise.config;

import org.glassfish.jersey.server.ResourceConfig;
import tech.financial.cloud.codingexercise.rest.PaymentResourceController;

public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(PaymentResourceController.class);
  }
}
