package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


import static py.com.progweb.prueba.utils.Utils.sumarFechaDias;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    //eesta anotacion es cuando requerimos que sea atomico el metodo
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void crearCliente(Cliente cliente) {
        cliente.setFechaNacimiento(sumarFechaDias(cliente.getFechaNacimiento(),1));
        em.persist(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        cliente.setFechaNacimiento(sumarFechaDias(cliente.getFechaNacimiento(),1));
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

    public List<Cliente> buscarClientes(String nombre, String apellido, Date fechaNacimiento) {
        String jpql = "SELECT c FROM Cliente c WHERE ";
        List<String> clausulas = new ArrayList<>();
        if (nombre != null && !nombre.isEmpty()) {
            clausulas.add("c.nombre LIKE :nombre");
        }
        if (apellido != null && !apellido.isEmpty()) {
            clausulas.add("c.apellido LIKE :apellido");
        }
        if (fechaNacimiento != null) {
            clausulas.add("c.fechaNacimiento = :fechaNacimiento");
        }
        if (clausulas.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un criterio de búsqueda");
        }
        jpql += String.join(" AND ", clausulas);
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        if (nombre != null && !nombre.isEmpty()) {
            query.setParameter("nombre", "%" + nombre + "%");
        }
        if (apellido != null && !apellido.isEmpty()) {
            query.setParameter("apellido", "%" + apellido + "%");
        }
        if (fechaNacimiento != null) {
            query.setParameter("fechaNacimiento", fechaNacimiento);
        }
        return query.getResultList();
    }
}
