package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.DTO.BolsaPuntosDTO;
import py.com.progweb.prueba.DTO.UsoPuntosDTO;
import py.com.progweb.prueba.ejb.*;
import py.com.progweb.prueba.model.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServiciosRest {

    private static final Logger LOGGER = Logger.getLogger(ServiciosRest.class.getName());

    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    @Inject
    ReglasAsigPuntosDAO reglasAsigPuntosDAO;
    @Inject
    private UsoPuntosCabeceraDAO usoPuntosCabeceraDAO;

    @Inject
    private ConceptoPuntosDAO conceptoPuntosDAO;

    @Inject
    private ClienteDAO clienteDAO;


    @GET
    @Path("saludo")
    public Response hola() {
        return Response.ok("hola desde ServiciosRest").build();
    }

    @POST
    @Path("/carga-puntos")
    public Response cargaPuntos(BolsaPuntosDTO bolsaPuntosDTO) {
        BolsaPuntos retorno = bolsaPuntosDAO.cargarPuntos(bolsaPuntosDTO.idCliente, bolsaPuntosDTO.monto);
        LOGGER.info("Bolsa agregada" + retorno);
        return Response.ok(retorno).build();
    }

    @POST
    @Path("/uso-puntos")
    public Response usoPuntos(UsoPuntosDTO usoPuntosDTO) {
        UsoPuntosCabecera retorno = bolsaPuntosDAO.usarPuntos(usoPuntosDTO.idCliente, usoPuntosDTO.idConceptoPuntos);
        LOGGER.info("Bolsa usada " + retorno);
        return Response.ok(Objects.requireNonNullElse(retorno, "{ \"mensaje\": \"El usuario no tiene saldo suficiente para aplicar a este concepto\" }")).build();
    }

    @GET
    @Path("/conv-monto/{monto}")
    public Response convMonto(@PathParam("monto") Integer monto) {
        return Response.ok(reglasAsigPuntosDAO.obtenerPuntosPorMonto(monto)).build();
    }

    @GET
    @Path("/usopuntos/cabecera/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsoPuntosConDetalles(@PathParam("id") Integer idCabecera) {
        UsoPuntosCabecera cabecera = usoPuntosCabeceraDAO.buscarPorId(idCabecera);
        if (cabecera == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<UsoPuntosDetalle> detalles = usoPuntosCabeceraDAO.obtenerDetallesPorCabecera(cabecera);

        Response.ResponseBuilder responseBuilder = Response.ok(cabecera);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("cabecera", cabecera);
        responseMap.put("detalles", detalles);
        responseBuilder.entity(responseMap);
        return responseBuilder.build();
    }


    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorIdConceptoPuntosFechaIdCliente(@QueryParam("idConceptoPuntos") Integer idConceptoPuntos,
                                                            @QueryParam("fecha") String fecha,
                                                            @QueryParam("idCliente") Integer idCliente) throws ParseException {
        ConceptoPuntos conceptoPuntos = null;
        Date fechaDate = null;
        Cliente cliente = null;

        if (idConceptoPuntos != null) {
            conceptoPuntos = conceptoPuntosDAO.obtenerConceptoPuntoPorId(idConceptoPuntos);
            if (conceptoPuntos == null) {
                return Response.ok("No existe el conceptoPuntos").build();
            }
        }

        if (fecha != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            fechaDate = formatter.parse(fecha);
        }

        if (idCliente != null) {
            cliente = clienteDAO.obtenerClientePorId(idCliente);
            if (cliente == null) {
                return Response.ok("No existe el cliente").build();
            }
        }

        List<UsoPuntosCabecera> cabeceras = usoPuntosCabeceraDAO.buscarPorIdConceptoPuntosFechaIdCliente(conceptoPuntos, fechaDate, cliente);
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (UsoPuntosCabecera cabecera : cabeceras) {
            List<UsoPuntosDetalle> detalles = usoPuntosCabeceraDAO.obtenerDetallesPorCabecera(cabecera);

            Map<String, Object> mapaCabecera = new HashMap<>();
            mapaCabecera.put("idUsoPuntosCabecera", cabecera.getIdUsoPuntosCabecera());
            mapaCabecera.put("fecha", cabecera.getFecha());
            mapaCabecera.put("idCliente", cabecera.getIdCliente());
            mapaCabecera.put("idConceptoPuntos", cabecera.getIdConceptoPuntos());
            mapaCabecera.put("puntajeUtilizado", cabecera.getPuntajeUtilizado());
            mapaCabecera.put("detalles", detalles);

            resultado.add(mapaCabecera);
        }

        return Response.ok(resultado).build();
    }

}
