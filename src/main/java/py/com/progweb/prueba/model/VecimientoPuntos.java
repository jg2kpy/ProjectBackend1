package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "vecimientoPuntos")
public class VecimientoPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_VecimientoPuntos")
    @GeneratedValue(generator="vecimientoPuntos_sec")
    @SequenceGenerator(name="vecimientoPuntos_sec",sequenceName="vecimientoPuntos_sec",allocationSize=0)
    private Integer idReglasAsigPuntos;

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

    public VecimientoPuntos() {

    }
}