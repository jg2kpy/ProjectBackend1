package py.com.progweb.prueba.model;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "bolsaPuntos")
public class BolsaPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_bolsaPuntos")
    @GeneratedValue(generator="bolsaPuntosSec")
    @SequenceGenerator(name="bolsaPuntosSec",sequenceName="bolsaPuntos_sec",allocationSize=0)
    private Integer idBolsaPuntos;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente",referencedColumnName = "id_cliente")
    private Cliente idCliente;

    @Basic(optional = false)
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;

    @Basic(optional = false)
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;

    @Basic(optional = false)
    @Column(name = "puntaje_asignado")
    private Integer puntajeAsignado;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @Basic(optional = false)
    @Column(name = "saldo_puntos")
    private Integer saldoPuntos;

    @Basic(optional = false)
    @Column(name = "monto_operacion")
    private Integer montoOperacion;

    public BolsaPuntos(){

    }
}
