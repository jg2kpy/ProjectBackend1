package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.ConceptoPuntos;
import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.UsoPuntosDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class UsoPuntosCabeceraDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<UsoPuntosDetalle> obtenerDetallesPorCabecera(UsoPuntosCabecera cabecera) {

        Query query = em.createQuery("SELECT d FROM UsoPuntosDetalle d WHERE d.idUsoPuntosCabecera = :cabecera");
        query.setParameter("cabecera", cabecera);
        return (List<UsoPuntosDetalle>) query.getResultList();

    }

    public UsoPuntosCabecera buscarPorId(Integer id) {
            return em.find(UsoPuntosCabecera.class, id);
    }

    public List<UsoPuntosCabecera> buscarPorIdConceptoPuntosFechaIdCliente(ConceptoPuntos idConceptoPuntos, Date fecha, Cliente idCliente) {
        String jpql = "SELECT c FROM UsoPuntosCabecera c WHERE ";
        List<String> clausulas = new ArrayList<>();
        if (idConceptoPuntos != null) {
            clausulas.add("c.idConceptoPuntos = :idConceptoPuntos");
        }
        if (fecha != null) {
            clausulas.add("c.fecha = :fecha");
        }
        if (idCliente != null) {
            clausulas.add("c.idCliente = :idCliente");
        }
        if (clausulas.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un criterio de búsqueda");
        }
        jpql += String.join(" AND ", clausulas);
        TypedQuery<UsoPuntosCabecera> query = em.createQuery(jpql, UsoPuntosCabecera.class);
        if (idConceptoPuntos != null) {
            query.setParameter("idConceptoPuntos", idConceptoPuntos);
        }
        if (fecha != null ) {
            query.setParameter("fecha", fecha);
        }
        if (idCliente != null) {
            query.setParameter("idCliente", idCliente);
        }
        return query.getResultList();
    }


}
