package tech.financial.cloud.codingexercise.rest;

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
        errorMessage.setMessage(getInitialCause(ex));
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private String getInitialCause(Throwable ex) {
        String message = null;
        while (ex != null) {
            message = ex.getMessage();
            ex = ex.getCause();
        }
        return message;
    }
}