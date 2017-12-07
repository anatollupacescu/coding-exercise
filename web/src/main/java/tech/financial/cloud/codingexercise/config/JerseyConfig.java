package tech.financial.cloud.codingexercise.config;

import org.glassfish.jersey.server.ResourceConfig;
import tech.financial.cloud.codingexercise.rest.PaymentResourceController;
import tech.financial.cloud.codingexercise.rest.RuntimeExceptionMapper;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(PaymentResourceController.class);
        register(RuntimeExceptionMapper.class);
    }
}
