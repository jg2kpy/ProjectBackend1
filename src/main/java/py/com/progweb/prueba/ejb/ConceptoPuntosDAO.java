package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ConceptoPuntosDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    //eesta anotacion es cuando requerimos que sea atomico el metodo
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void crearConceptoPunto(ConceptoPuntos conceptoPunto) {
        em.persist(conceptoPunto);
    }

    public void actualizarConceptoPunto(ConceptoPuntos conceptoPunto) {
        em.merge(conceptoPunto);
    }

    public void eliminarConceptoPunto(ConceptoPuntos conceptoPunto) {
        em.remove(em.merge(conceptoPunto));
    }

    public List<ConceptoPuntos> listarTodosLosConceptoPuntos() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ConceptoPuntos> criteriaQuery = criteriaBuilder.createQuery(ConceptoPuntos.class);
        Root<ConceptoPuntos> root = criteriaQuery.from(ConceptoPuntos.class);
        criteriaQuery.select(root);
        TypedQuery<ConceptoPuntos> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public ConceptoPuntos obtenerConceptoPuntoPorId(Integer id) {
        return em.find(ConceptoPuntos.class, id);
    }
}
