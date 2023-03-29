package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class VencimientoPuntosDAO {

    @PersistenceContext(unitName="pruebaPU")
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

    public List<VencimientoPuntos> listarTodosLosVencimientoPuntos() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<VencimientoPuntos> criteriaQuery = criteriaBuilder.createQuery(VencimientoPuntos.class);
        Root<VencimientoPuntos> root = criteriaQuery.from(VencimientoPuntos.class);
        criteriaQuery.select(root);
        TypedQuery<VencimientoPuntos> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public VencimientoPuntos obtenerVencimientoPuntosPorId(Integer id) {
        return em.find(VencimientoPuntos.class, id);
    }

}
