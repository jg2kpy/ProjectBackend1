package py.com.progweb.prueba.utils;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.logging.Logger;

@Singleton
public class ScheduleVencimiento {

    private static final Logger LOGGER = Logger.getLogger(ScheduleVencimiento.class.getName());

    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    //cada 3 horas
    //@Schedule(hour="*/3")

    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void actualizarVencidos() {
        LOGGER.info("Actualizando bolsas vencidas");
        bolsaPuntosDAO.actualizarVencidos();
    }
}
