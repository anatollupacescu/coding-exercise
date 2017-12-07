package tech.financial.cloud.codingexercise.rest;

import org.springframework.beans.factory.annotation.Autowired;
import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/v1/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResourceController {

    @Autowired
    private PaymentResourceService service;

    @GET
    public ApiResponse listPayments(@Context UriInfo uriInfo) {
        ApiResponse response = new ApiResponse();
        List<PaymentResource> payments = service.getAll();
        response.setData(payments);
        URI absolutePath = uriInfo.getAbsolutePathBuilder().build();
        response.setLinks(ApiResponse.newLinks(absolutePath.toString()));
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePayment(PaymentResource resource, @Context UriInfo uriInfo) {
        PaymentResource created = service.create(resource);
        URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId().toString()).build();
        return Response.created(uri).entity(created).build();
    }
}
