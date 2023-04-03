package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

@Path("cliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteRest {

    @Inject
    ClienteDAO clienteDAO;

    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;


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
    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> buscarClientes(@QueryParam("nombre") String nombre,
                                        @QueryParam("apellido") String apellido,
                                        @QueryParam("fechaNacimiento") String fechaNacimiento) throws ParseException {
        Date fecha = null;
        if(fechaNacimiento != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            fecha = formatter.parse(fechaNacimiento);
        }
        return clienteDAO.buscarClientes(nombre, apellido, fecha);
    }

    @GET
    @Path("/puntos-a-vencer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerClientesConPuntosAVencerEnXDias(@QueryParam("dias") int dias) {
        Date fechaActual = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaActual);
        c.add(Calendar.DATE, dias);
        Date fechaLimite = c.getTime();
        List<BolsaPuntos> bolsas = bolsaPuntosDAO.obtenerBolsasConFechaCaducidadEnRango(fechaActual, fechaLimite);
        Set<Cliente> clientesConPuntosAVencer = new HashSet<>();
        for (BolsaPuntos bolsa : bolsas) {
            clientesConPuntosAVencer.add(bolsa.getIdCliente());
        }
        return new ArrayList<>(clientesConPuntosAVencer);
    }

    @GET
    @Path("/bolsa-puntos/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerBolsasPorCliente(@PathParam("idCliente") int idCliente) {
        List<BolsaPuntos> bolsas = bolsaPuntosDAO.obtenerBolsasPorClienteYRangoDePuntos(idCliente, -1, -1);
        return Response.ok(bolsas).build();
    }

    @GET
    @Path("/bolsa-puntos/rango")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerBolsasPorRangoDePuntos(@QueryParam("minPuntos") int minPuntos, @QueryParam("maxPuntos") int maxPuntos) {
        List<BolsaPuntos> bolsas = bolsaPuntosDAO.obtenerBolsasPorClienteYRangoDePuntos(-1, minPuntos, maxPuntos);
        return Response.ok(bolsas).build();
    }

    @GET
    @Path("/bolsa-puntos/{idCliente}/rango")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerBolsasPorClienteYRangoDePuntos(@PathParam("idCliente") int idCliente, @QueryParam("minPuntos") int minPuntos, @QueryParam("maxPuntos") int maxPuntos) {
        List<BolsaPuntos> bolsas = bolsaPuntosDAO.obtenerBolsasPorClienteYRangoDePuntos(idCliente, minPuntos, maxPuntos);
        return Response.ok(bolsas).build();
    }
}
