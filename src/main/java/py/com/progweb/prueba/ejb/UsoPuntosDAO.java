package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.UsoPuntosDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void crearUsoPuntosCabecera(UsoPuntosCabecera usoPuntosCabecera) {
        em.persist(usoPuntosCabecera);
    }

    public void crearUsoPuntosDetalle(UsoPuntosDetalle usoPuntosDetalle) {
        em.persist(usoPuntosDetalle);
    }

}
