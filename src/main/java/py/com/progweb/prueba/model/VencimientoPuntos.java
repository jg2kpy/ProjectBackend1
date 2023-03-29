package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "vencimientoPuntos")
public class VencimientoPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_VencimientoPuntos")
    @GeneratedValue(generator="vencimientoPuntos_sec")
    @SequenceGenerator(name="vencimientoPuntos_sec",sequenceName="vencimientoPuntos_sec",allocationSize=0)
    private Integer idVencimientoPuntos;

    @Basic(optional = false)
    @Column(name = "fecha_inicio_validez")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioValidez;

    @Basic(optional = false)
    @Column(name = "fecha_fin_validez")
    @Temporal(TemporalType.DATE)
    private Date fechaFinValidez;

    @Basic(optional = false)
    @Column(name = "dias_duracion")
    private Integer diasDuracion;

    public void setIdVencimientoPuntos(Integer idVencimientoPuntos) {
        this.idVencimientoPuntos = idVencimientoPuntos;
    }

    public VencimientoPuntos() {

    }
}