package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {

    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

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

    public BolsaPuntos cargarPuntos(Integer idCliente, Integer monto){
        Date fechaDeHoy = new Date();

        BolsaPuntos retorno = new BolsaPuntos();
        retorno.setIdCliente(clienteDAO.obtenerClientePorId(idCliente));
        retorno.setFechaAsignacion(fechaDeHoy);

        int diasDuracion = vencimientoPuntosDAO.obtenerDiasDuracionPorFecha(fechaDeHoy);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        calendario.add(Calendar.DATE, diasDuracion);
        retorno.setFechaCaducidad(calendario.getTime());

        retorno.setPuntajeAsignado(reglasAsigPuntosDAO.obtenerPuntosPorMonto(monto));

        retorno.setPuntajeUtilizado(0);
        retorno.setSaldoPuntos(retorno.getPuntajeAsignado());
        retorno.setMontoOperacion(monto);
        em.persist(retorno);//Guarda en la base de datos
        return retorno;
    }

    public UsoPuntosCabecera usarPuntos(Integer idCliente, Integer idConceptoPuntos){
        Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);
        ConceptoPuntos conceptoPuntos = conceptoPuntosDAO.obtenerConceptoPuntoPorId(idConceptoPuntos);

        Date fechaDeHoy = new Date();
        int puntajeDescontar = conceptoPuntos.getPuntosRequeridos();
        List<BolsaPuntos> bolsas = obtenerBolsasPorCliente(cliente);

        int saldoTotal = 0;
        for(BolsaPuntos bolsa: bolsas){
            saldoTotal += bolsa.getSaldoPuntos();
        }

        if (saldoTotal < puntajeDescontar){
            return null;
        }

        UsoPuntosCabecera usoPuntosCabecera = new UsoPuntosCabecera();
        usoPuntosCabecera.setIdCliente(cliente);
        usoPuntosCabecera.setPuntajeUtilizado(puntajeDescontar);
        usoPuntosCabecera.setFecha(fechaDeHoy);
        usoPuntosCabecera.setIdConceptoPuntos(conceptoPuntos);

        usoPuntosDAO.crearUsoPuntosCabecera(usoPuntosCabecera);

        for(BolsaPuntos bolsa: bolsas){
            int saldoBolsa = bolsa.getSaldoPuntos();
            UsoPuntosDetalle usoPuntosDetalle;

            if(puntajeDescontar > saldoBolsa){
                puntajeDescontar -= saldoBolsa;
                bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado() + saldoBolsa);
                bolsa.setSaldoPuntos(0);
                usoPuntosDetalle = new UsoPuntosDetalle(usoPuntosCabecera, saldoBolsa, bolsa);
                usoPuntosDAO.crearUsoPuntosDetalle(usoPuntosDetalle);
            }else{
                bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado() + puntajeDescontar);
                bolsa.setSaldoPuntos(saldoBolsa - puntajeDescontar);
                usoPuntosDetalle = new UsoPuntosDetalle(usoPuntosCabecera, puntajeDescontar, bolsa);
                usoPuntosDAO.crearUsoPuntosDetalle(usoPuntosDetalle);
                break;
            }
        }

        //Enviar un correo antes del retorno

        return usoPuntosCabecera;
    }

    private List<BolsaPuntos> obtenerBolsasPorCliente(Cliente cliente){
        Query query = em.createQuery("SELECT b FROM BolsaPuntos b WHERE b.idCliente = :cliente order by b.fechaAsignacion ASC");
        query.setParameter("cliente", cliente);
        return (List<BolsaPuntos>) query.getResultList();
    }

}
