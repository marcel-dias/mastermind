package com.marceldias.exception;

import com.marceldias.model.ResponseMessage;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException e) {
        ResponseMessage msg = new ResponseMessage(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
    }
}
