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
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;

    @Basic(optional = false)
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
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

    public Integer getIdBolsaPuntos() {
        return idBolsaPuntos;
    }

    public void setIdBolsaPuntos(Integer idBolsaPuntos) {
        this.idBolsaPuntos = idBolsaPuntos;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Integer getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public BolsaPuntos(){

    }
}
