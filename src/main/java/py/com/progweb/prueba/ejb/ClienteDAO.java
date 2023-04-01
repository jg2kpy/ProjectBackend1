package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public List listarTodosLosClientes() {
        Query query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }

    public Cliente obtenerClientePorId(Integer id) {
        return em.find(Cliente.class, id);
    }
}
