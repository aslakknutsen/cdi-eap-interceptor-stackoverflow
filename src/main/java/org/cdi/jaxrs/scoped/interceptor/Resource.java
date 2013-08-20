package org.cdi.jaxrs.scoped.interceptor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ResourceModel
@Path("/test")
public class Resource {

    @GET
    @Path("/")
    public Response test() {
        return Response.ok().build();
    }
}
