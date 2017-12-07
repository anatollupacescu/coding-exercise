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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Path("/v1/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResourceController {

    @Autowired
    private PaymentResourceService service;

    @GET
    public ApiResponse listPayments(@Context UriInfo uriInfo) {
        List<PaymentResource> payments = service.getAll();
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        ApiResponse response = newApiResponse(payments, uri);
        return response;
    }

    @GET
    @Path("/{uuidString}")
    public ApiResponse getSinglePayment(@PathParam("uuidString") String uuidString, @Context UriInfo uriInfo) {
        UUID id = UUID.fromString(uuidString);
        PaymentResource paymentResource = service.getById(id);
        URI uri = uriInfo.getAbsolutePathBuilder().path(uuidString).build();
        ApiResponse response = newApiResponse(Collections.singletonList(paymentResource), uri);
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePayment(PaymentResource resource, @Context UriInfo uriInfo) {
        PaymentResource created = service.create(resource);
        URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId().toString()).build();
        return Response.created(uri).entity(created).build();
    }

    @DELETE
    @Path("/{uuidString}")
    public Response deletePayment(@PathParam("uuidString") String uuidString) {
        UUID id = UUID.fromString(uuidString);
        service.delete(id);
        return Response.noContent().build();
    }

    private ApiResponse newApiResponse(List<PaymentResource> data, URI uri) {
        ApiResponse response = new ApiResponse();
        response.setData(data);
        response.setLinks(ApiResponse.newLinks(uri.toString()));
        return response;
    }
}
