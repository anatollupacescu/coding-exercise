package tech.financial.cloud.codingexercise.rest;

import org.springframework.beans.factory.annotation.Autowired;
import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResourceController {

    @Autowired
	private PaymentResourceService service;

	@GET
	public List<PaymentResource> listPayments() {
		return service.getAll();
	}
}
