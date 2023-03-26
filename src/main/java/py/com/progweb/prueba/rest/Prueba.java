package py.com.progweb.prueba.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author jg2kpy
 * <a href="https://github.com/jg2kpy">jg2kpy's Github</a>
 * <a href="https://juniorgutierrez.com/">jg2kpy's Personal Website</a>
 *
 */

@Path("/")
public class Prueba {
    @GET
    @Path("/")
    public String PruebaFn() {
        return "prueba XDDDDDDDDdDDDDDDDD";
    }
}
