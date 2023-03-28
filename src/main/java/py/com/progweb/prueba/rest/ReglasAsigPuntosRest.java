package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ReglasAsigPuntosDAO;
import py.com.progweb.prueba.model.ReglasAsigPuntos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("reglas-asig-puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReglasAsigPuntosRest {

    @Inject
    ReglasAsigPuntosDAO reglasAsigPuntosDAO;

    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola desde reglasAsigPuntos").build();
    }


    @POST
    @Path("/")
    public Response agregar(ReglasAsigPuntos entidad) {
        reglasAsigPuntosDAO.crearReglasAsigPuntos(entidad);
        return Response.ok(entidad).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarReglasAsigPuntos(@PathParam("id") Integer id, ReglasAsigPuntos reglasAsigPuntos) {
        ReglasAsigPuntos reglasAsigPuntosExistente = reglasAsigPuntosDAO.obtenerReglasAsigPuntosPorId(id);
        if (reglasAsigPuntosExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            reglasAsigPuntos.setIdReglasAsigPuntos(id);
            reglasAsigPuntosDAO.actualizarReglasAsigPuntos(reglasAsigPuntos);
            return Response.status(Response.Status.OK).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarReglasAsigPuntos(@PathParam("id") Integer id) {
        ReglasAsigPuntos reglasAsigPuntosExistente = reglasAsigPuntosDAO.obtenerReglasAsigPuntosPorId(id);
        if (reglasAsigPuntosExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            reglasAsigPuntosDAO.eliminarReglasAsigPuntos(reglasAsigPuntosExistente);
            return Response.status(Response.Status.OK).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLosReglasAsigPuntos() {
        List<ReglasAsigPuntos> reglasAsigPuntos = reglasAsigPuntosDAO.listarTodosLosReglasAsigPuntos();
        return Response.ok(reglasAsigPuntos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReglasAsigPuntosPorId(@PathParam("id") Integer id) {
        ReglasAsigPuntos reglasAsigPuntos = reglasAsigPuntosDAO.obtenerReglasAsigPuntosPorId(id);
        if (reglasAsigPuntos == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(reglasAsigPuntos).build();
        }
    }
}
