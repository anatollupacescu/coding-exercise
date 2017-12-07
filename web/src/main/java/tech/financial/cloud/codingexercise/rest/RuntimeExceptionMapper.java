package tech.financial.cloud.codingexercise.rest;

import lombok.Data;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        if (ex instanceof EntityNotFoundException) {
            errorMessage.setStatus(404);
        } else {
            errorMessage.setStatus(400);
        }
        errorMessage.setMessage(ex.getMessage());
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Data
    class ErrorMessage {
        int status;
        String message;
    }
}