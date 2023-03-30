package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    //eesta anotacion es cuando requerimos que sea atomico el metodo
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void crearCliente(Cliente cliente) {
        em.persist(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        em.merge(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        em.remove(em.merge(cliente));
    }

    public List<Cliente> listarTodosLosClientes() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);
        criteriaQuery.select(root);
        TypedQuery<Cliente> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public Cliente obtenerClientePorId(Integer id) {
        return em.find(Cliente.class, id);
    }
}
