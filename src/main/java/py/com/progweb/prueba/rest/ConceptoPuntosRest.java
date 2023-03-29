package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ConceptoPuntosDAO;
import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("concepto-puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ConceptoPuntosRest {

    @Inject
    ConceptoPuntosDAO conceptoPuntoDAO;

    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola desde conceptoPunto").build();
    }


    @POST
    @Path("/")
    public Response agregar(ConceptoPuntos entidad) {
        conceptoPuntoDAO.crearConceptoPunto(entidad);
        return Response.ok(entidad).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarConceptoPunto(@PathParam("id") Integer id, ConceptoPuntos conceptoPunto) {
        ConceptoPuntos conceptoPuntoExistente = conceptoPuntoDAO.obtenerConceptoPuntoPorId(id);
        if (conceptoPuntoExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            conceptoPunto.setIdConceptoPuntos(id);
            conceptoPuntoDAO.actualizarConceptoPunto(conceptoPunto);
            return Response.status(Response.Status.OK).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarConceptoPunto(@PathParam("id") Integer id) {
        ConceptoPuntos conceptoPuntoExistente = conceptoPuntoDAO.obtenerConceptoPuntoPorId(id);
        if (conceptoPuntoExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            conceptoPuntoDAO.eliminarConceptoPunto(conceptoPuntoExistente);
            return Response.status(Response.Status.OK).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLosConceptoPuntos() {
        List<ConceptoPuntos> conceptoPuntos = conceptoPuntoDAO.listarTodosLosConceptoPuntos();
        return Response.ok(conceptoPuntos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerConceptoPuntoPorId(@PathParam("id") Integer id) {
        ConceptoPuntos conceptoPunto = conceptoPuntoDAO.obtenerConceptoPuntoPorId(id);
        if (conceptoPunto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(conceptoPunto).build();
        }
    }
}
