package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("vencimiento-puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VencimientoPuntosRest {

    @Inject
    VencimientoPuntosDAO vencimientoPuntosDAO;

    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola desde vencimientoPuntos").build();
    }


    @POST
    @Path("/")
    public Response agregar(VencimientoPuntos entidad) {
        vencimientoPuntosDAO.crearVencimientoPuntos(entidad);
        return Response.ok(entidad).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarVencimientoPuntos(@PathParam("id") Integer id, VencimientoPuntos vencimientoPuntos) {
        VencimientoPuntos vencimientoPuntosExistente = vencimientoPuntosDAO.obtenerVencimientoPuntosPorId(id);
        if (vencimientoPuntosExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            vencimientoPuntos.setIdVencimientoPuntos(id);
            vencimientoPuntosDAO.actualizarVencimientoPuntos(vencimientoPuntos);
            return Response.status(Response.Status.OK).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarVencimientoPuntos(@PathParam("id") Integer id) {
        VencimientoPuntos vencimientoPuntosExistente = vencimientoPuntosDAO.obtenerVencimientoPuntosPorId(id);
        if (vencimientoPuntosExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            vencimientoPuntosDAO.eliminarVencimientoPuntos(vencimientoPuntosExistente);
            return Response.status(Response.Status.OK).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLosVencimientoPuntos() {
        List<VencimientoPuntos> vencimientoPuntos = vencimientoPuntosDAO.listarTodosLosVencimientoPuntos();
        return Response.ok(vencimientoPuntos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerVencimientoPuntosPorId(@PathParam("id") Integer id) {
        VencimientoPuntos vencimientoPuntos = vencimientoPuntosDAO.obtenerVencimientoPuntosPorId(id);
        if (vencimientoPuntos == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(vencimientoPuntos).build();
        }
    }

}
