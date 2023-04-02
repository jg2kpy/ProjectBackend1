package py.com.progweb.prueba.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {
    static public Date sumarFechaDias(Date Fecha, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(Fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }
}
