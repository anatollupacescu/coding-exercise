package tech.financial.cloud.codingexercise.rest;

import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/v1/payments")
public class PaymentResourceController {

	private final PaymentResourceService service;

	public PaymentResourceController(PaymentResourceService service) {
		this.service = service;
	}

	@GET
	public List<PaymentResource> message() {
		return service.getAll();
	}
}
