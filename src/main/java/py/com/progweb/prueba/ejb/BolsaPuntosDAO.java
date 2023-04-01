package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.*;
import py.com.progweb.prueba.utils.EmailUitls;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static py.com.progweb.prueba.utils.utils.sumarFechaDias;

@Stateless
public class BolsaPuntosDAO {

    @Inject
    ClienteDAO clienteDAO;
    @Inject
    VencimientoPuntosDAO vencimientoPuntosDAO;
    @Inject
    ReglasAsigPuntosDAO reglasAsigPuntosDAO;
    @Inject
    ConceptoPuntosDAO conceptoPuntosDAO;
    @Inject
    UsoPuntosDAO usoPuntosDAO;
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List listarTodasLasBolsas() {
        Query query = em.createQuery("SELECT bp FROM BolsaPuntos bp", BolsaPuntos.class);
        return query.getResultList();
    }

    public BolsaPuntos cargarPuntos(Integer idCliente, Integer monto) {
        Date fechaDeHoy = new Date();

        BolsaPuntos retorno = new BolsaPuntos();
        retorno.setIdCliente(clienteDAO.obtenerClientePorId(idCliente));
        retorno.setFechaAsignacion(fechaDeHoy);

        int diasDuracion = vencimientoPuntosDAO.obtenerDiasDuracionPorFecha(fechaDeHoy);
        retorno.setFechaCaducidad(sumarFechaDias(fechaDeHoy, diasDuracion));

        retorno.setPuntajeAsignado(reglasAsigPuntosDAO.obtenerPuntosPorMonto(monto));

        retorno.setPuntajeUtilizado(0);
        retorno.setSaldoPuntos(retorno.getPuntajeAsignado());
        retorno.setMontoOperacion(monto);
        em.persist(retorno);//Guarda en la base de datos
        return retorno;
    }

    public UsoPuntosCabecera usarPuntos(Integer idCliente, Integer idConceptoPuntos) {
        Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);
        ConceptoPuntos conceptoPuntos = conceptoPuntosDAO.obtenerConceptoPuntoPorId(idConceptoPuntos);

        Date fechaDeHoy = new Date();
        int puntajeDescontar = conceptoPuntos.getPuntosRequeridos();
        List<BolsaPuntos> bolsas = obtenerBolsasPorCliente(cliente);

        int saldoTotal = 0;
        for (BolsaPuntos bolsa : bolsas) {
            saldoTotal += bolsa.getSaldoPuntos();
        }

        if (saldoTotal < puntajeDescontar) {
            return null;
        }

        UsoPuntosCabecera usoPuntosCabecera = new UsoPuntosCabecera();
        usoPuntosCabecera.setIdCliente(cliente);
        usoPuntosCabecera.setPuntajeUtilizado(puntajeDescontar);
        usoPuntosCabecera.setFecha(fechaDeHoy);
        usoPuntosCabecera.setIdConceptoPuntos(conceptoPuntos);

        usoPuntosDAO.crearUsoPuntosCabecera(usoPuntosCabecera);

        for (BolsaPuntos bolsa : bolsas) {
            int saldoBolsa = bolsa.getSaldoPuntos();
            UsoPuntosDetalle usoPuntosDetalle;
            if (saldoBolsa>0) {
                if (puntajeDescontar > saldoBolsa) {
                    puntajeDescontar -= saldoBolsa;
                    bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado() + saldoBolsa);
                    bolsa.setSaldoPuntos(0);
                    usoPuntosDetalle = new UsoPuntosDetalle(usoPuntosCabecera, saldoBolsa, bolsa);
                    usoPuntosDAO.crearUsoPuntosDetalle(usoPuntosDetalle);
                } else {
                    bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado() + puntajeDescontar);
                    bolsa.setSaldoPuntos(saldoBolsa - puntajeDescontar);
                    usoPuntosDetalle = new UsoPuntosDetalle(usoPuntosCabecera, puntajeDescontar, bolsa);
                    usoPuntosDAO.crearUsoPuntosDetalle(usoPuntosDetalle);
                    break;
                }
            }
        }

        String cuerpo = cliente.toString() + "\n\n" + usoPuntosCabecera.toString();
        EmailUitls.enviarCorreo(System.getenv("user"), cliente.getEmail(), "Uso de Puntos", cuerpo);

        return usoPuntosCabecera;
    }

    private List<BolsaPuntos> obtenerBolsasPorCliente(Cliente cliente) {
        Query query = em.createQuery("SELECT b FROM BolsaPuntos b WHERE b.idCliente = :cliente order by b.fechaAsignacion ASC");
        query.setParameter("cliente", cliente);
        return (List<BolsaPuntos>) query.getResultList();
    }

    public void actualizarVencidos() {
        List<BolsaPuntos> bolsas = listarTodasLasBolsas();
        Date fechaDeHoy = new Date();

        for (BolsaPuntos bolsa : bolsas) {
            if (fechaDeHoy.after(bolsa.getFechaCaducidad())){
                bolsa.setSaldoPuntos(0);
            }
        }

    }
}
