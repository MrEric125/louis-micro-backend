package org.louis.micro;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 * @author John·Louis
 *  created at 2019/9/14
 * description:
 */
@Path("/default")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8", MediaType.TEXT_XML + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8"})
public interface DemoService {


    @GET
    @Path("selectByOpenId/{name}")
    String sayHello(@PathParam("name") String name);
}
