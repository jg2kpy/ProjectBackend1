package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.BolsaPuntos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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

    public BolsaPuntos cargarPuntos(int idCliente, int monto){
        Date fechaDeHoy = new Date();

        BolsaPuntos retorno = new BolsaPuntos();
        retorno.setIdCliente(clienteDAO.obtenerClientePorId(idCliente));
        retorno.setFechaAsignacion(fechaDeHoy);

        int diasDuracion = vencimientoPuntosDAO.obtenerDiasDuracionPorFecha(fechaDeHoy);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        calendario.add(Calendar.DATE, diasDuracion);
        retorno.setFechaCaducidad(calendario.getTime());

        System.out.println(retorno.getFechaCaducidad());

        retorno.setPuntajeAsignado(reglasAsigPuntosDAO.obtenerPuntosPorMonto(monto));

        retorno.setPuntajeUtilizado(0);
        retorno.setSaldoPuntos(retorno.getPuntajeAsignado());
        retorno.setMontoOperacion(monto);
        em.persist(retorno);//Guarda en la base de datos
        return retorno;
    }

}
