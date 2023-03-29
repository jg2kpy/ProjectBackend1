package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.ReglasAsigPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ReglasAsigPuntosDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    //eesta anotacion es cuando requerimos que sea atomico el metodo
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void crearReglasAsigPuntos(ReglasAsigPuntos reglasAsigPuntos) {
        em.persist(reglasAsigPuntos);
    }

    public void actualizarReglasAsigPuntos(ReglasAsigPuntos reglasAsigPuntos) {
        em.merge(reglasAsigPuntos);
    }

    public void eliminarReglasAsigPuntos(ReglasAsigPuntos reglasAsigPuntos) {
        em.remove(em.merge(reglasAsigPuntos));
    }

    public List<ReglasAsigPuntos> listarTodosLosReglasAsigPuntos() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ReglasAsigPuntos> criteriaQuery = criteriaBuilder.createQuery(ReglasAsigPuntos.class);
        Root<ReglasAsigPuntos> root = criteriaQuery.from(ReglasAsigPuntos.class);
        criteriaQuery.select(root);
        TypedQuery<ReglasAsigPuntos> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public ReglasAsigPuntos obtenerReglasAsigPuntosPorId(Integer id) {
        return em.find(ReglasAsigPuntos.class, id);
    }
}
