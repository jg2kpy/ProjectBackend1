package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PersonaDAO;
import py.com.progweb.prueba.model.Persona;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("persona")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PersonaRest {

    @Inject
    PersonaDAO personaDAO;

    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola").build();
    }


    @POST
    @Path("/")
    public Response agregar(Persona entidad) {
        personaDAO.agregar(entidad);
        return Response.ok(entidad).build();
    }

    @GET
    @Path("/")
    public Response lista(@QueryParam("nombre") String nombre) {
        return Response.ok(personaDAO.lista(nombre)).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(personaDAO.getById(id)).build();
    }
}
