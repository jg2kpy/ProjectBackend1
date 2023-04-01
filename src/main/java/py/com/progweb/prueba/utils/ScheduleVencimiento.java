package py.com.progweb.prueba.utils;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class ScheduleVencimiento {
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    //cada 3 horas
    //@Schedule(hour="*/3")

    @Schedule(second="*/10", minute="*", hour="*")
    public void actualizarVencidos() {
        bolsaPuntosDAO.actualizarVencidos();
    }
}
