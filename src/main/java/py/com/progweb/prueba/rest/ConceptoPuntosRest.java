package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ConceptoPuntosDAO;
import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("concepto-puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ConceptoPuntosRest {

    private static final Logger LOGGER = Logger.getLogger(ConceptoPuntosRest.class.getName());

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
        LOGGER.info("Concepto agregado " + entidad);
        return Response.ok(entidad).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarConceptoPunto(@PathParam("id") Integer id, ConceptoPuntos conceptoPunto) {
        ConceptoPuntos conceptoPuntoExistente = conceptoPuntoDAO.obtenerConceptoPuntoPorId(id);
        if (conceptoPuntoExistente == null) {
            LOGGER.warning("Concepto no existe");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            conceptoPunto.setIdConceptoPuntos(id);
            conceptoPuntoDAO.actualizarConceptoPunto(conceptoPunto);
            LOGGER.info("Concepto actualizado " + conceptoPunto);
            return Response.status(Response.Status.OK).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarConceptoPunto(@PathParam("id") Integer id) {
        ConceptoPuntos conceptoPuntoExistente = conceptoPuntoDAO.obtenerConceptoPuntoPorId(id);
        if (conceptoPuntoExistente == null) {
            LOGGER.warning("Concepto no existe");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            conceptoPuntoDAO.eliminarConceptoPunto(conceptoPuntoExistente);
            LOGGER.info("Concepto actualizado " + conceptoPuntoExistente);
            return Response.status(Response.Status.OK).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLosConceptoPuntos() {
        List conceptoPuntos = conceptoPuntoDAO.listarTodosLosConceptoPuntos();
        return Response.ok(conceptoPuntos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerConceptoPuntoPorId(@PathParam("id") Integer id) {
        ConceptoPuntos conceptoPunto = conceptoPuntoDAO.obtenerConceptoPuntoPorId(id);
        if (conceptoPunto == null) {
            LOGGER.warning("Concepto no existe");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            LOGGER.info("Concepto obtenido " + conceptoPunto);
            return Response.ok(conceptoPunto).build();
        }
    }
}
