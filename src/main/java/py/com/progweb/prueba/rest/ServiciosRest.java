package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.ReglasAsigPuntos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServiciosRest {

    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola desde ServiciosRest").build();
    }

    @POST
    @Path("/carga-puntos")
        public Response agregar(@FormParam("idCliente") int idCliente, @FormParam("monto") int monto) {
        BolsaPuntos retorno = bolsaPuntosDAO.cargarPuntos(idCliente, monto);
        return Response.ok(retorno).build();
    }

}
