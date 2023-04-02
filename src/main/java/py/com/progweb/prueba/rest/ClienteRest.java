package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Cliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteRest {

    @Inject
    ClienteDAO clienteDAO;

    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola desde cliente").build();
    }


    @POST
    @Path("/")
    public Response agregar(Cliente entidad) {
        clienteDAO.crearCliente(entidad);
        return Response.ok(entidad).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarCliente(@PathParam("id") Integer id, Cliente cliente) {
        Cliente clienteExistente = clienteDAO.obtenerClientePorId(id);
        if (clienteExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            cliente.setIdCliente(id);
            clienteDAO.actualizarCliente(cliente);
            return Response.status(Response.Status.OK).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarCliente(@PathParam("id") Integer id) {
        Cliente clienteExistente = clienteDAO.obtenerClientePorId(id);
        if (clienteExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            clienteDAO.eliminarCliente(clienteExistente);
            return Response.status(Response.Status.OK).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLosClientes() {
        List clientes = clienteDAO.listarTodosLosClientes();
        return Response.ok(clientes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientePorId(@PathParam("id") Integer id) {
        Cliente cliente = clienteDAO.obtenerClientePorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(cliente).build();
        }
    }
}
