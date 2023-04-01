package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class VencimientoPuntosDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void crearVencimientoPuntos(VencimientoPuntos vencimientoPuntos) {
        em.persist(vencimientoPuntos);
    }

    public void actualizarVencimientoPuntos(VencimientoPuntos vencimientoPuntos) {
        em.merge(vencimientoPuntos);
    }

    public void eliminarVencimientoPuntos(VencimientoPuntos vencimientoPuntos) {
        em.remove(em.merge(vencimientoPuntos));
    }

    public List listarTodosLosVencimientoPuntos() {
        Query query = em.createQuery("SELECT vp FROM VencimientoPuntos vp", VencimientoPuntos.class);
        return query.getResultList();
    }

    public VencimientoPuntos obtenerVencimientoPuntosPorId(Integer id) {
        return em.find(VencimientoPuntos.class, id);
    }

    public int obtenerDiasDuracionPorFecha(Date fecha) {
        Query query = em.createQuery("SELECT t.diasDuracion FROM VencimientoPuntos t WHERE t.fechaInicioValidez < :fecha AND t.fechaFinValidez > :fecha");
        query.setParameter("fecha", fecha);
        return (int) query.getResultList().get(0);
    }

}
