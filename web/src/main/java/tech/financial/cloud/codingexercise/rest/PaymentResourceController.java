package tech.financial.cloud.codingexercise.rest;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tech.financial.cloud.codingexercise.domain.api.PaymentResourceService;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

@Path("/v1/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResourceController {

    @Autowired
    private PaymentResourceService service;

    @GET
    public ApiResponse listPayments(@Context UriInfo uriInfo) {
        List<PaymentResource> payments = service.getAll();
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return newApiResponse(payments, uri);
    }

    @GET
    @Path("/{uuidString}")
    public ApiResponse getSinglePayment(@PathParam("uuidString") String uuidString, @Context UriInfo uriInfo) {
        UUID id = UUID.fromString(uuidString);
        PaymentResource paymentResource = service.getById(id);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return newApiResponse(Collections.singletonList(paymentResource), uri);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePayment(PaymentResource resource, @Context UriInfo uriInfo) {
        PaymentResource created = service.create(resource);
        URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId().toString()).build();
        ApiResponse response = newApiResponse(Collections.singletonList(created), uri);
        return Response.created(uri).entity(response).build();
    }

    @DELETE
    @Path("/{uuidString}")
    public Response deletePayment(@PathParam("uuidString") String uuidString) {
        UUID id = UUID.fromString(uuidString);
        service.delete(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{uuidString}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePayment(@PathParam("uuidString") String uuidString, PaymentResource resource) {
        UUID id = UUID.fromString(uuidString);
        service.update(id, resource);
        return Response.noContent().build();
    }

    private ApiResponse newApiResponse(List<PaymentResource> data, URI uri) {
        ApiResponse response = new ApiResponse();
        response.setData(data);
        response.setLinks(ApiResponse.newLinks(uri.toString()));
        return response;
    }
}
