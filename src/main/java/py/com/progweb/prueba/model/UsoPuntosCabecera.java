package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usoPuntosCabecera")
public class UsoPuntosCabecera {
    @Id
    @Basic(optional = false)
    @Column(name = "id_usoPuntosCabecera")
    @GeneratedValue(generator = "usoPuntosCabeceraSec")
    @SequenceGenerator(name = "usoPuntosCabeceraSec", sequenceName = "usoPuntosCabecera_sec", allocationSize = 0)
    private Integer idUsoPuntosCabecera;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente idCliente;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conceptoPuntos", referencedColumnName = "id_conceptoPuntos")
    private ConceptoPuntos idConceptoPuntos;

    public UsoPuntosCabecera() {

    }

    public UsoPuntosCabecera(Cliente idCliente, Integer puntajeUtilizado, Date fecha, ConceptoPuntos idConceptoPuntos) {
        this.idCliente = idCliente;
        this.puntajeUtilizado = puntajeUtilizado;
        this.fecha = fecha;
        this.idConceptoPuntos = idConceptoPuntos;
    }

    public Integer getIdUsoPuntosCabecera() {
        return idUsoPuntosCabecera;
    }

    public void setIdUsoPuntosCabecera(Integer idUsoPuntosCabecera) {
        this.idUsoPuntosCabecera = idUsoPuntosCabecera;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ConceptoPuntos getIdConceptoPuntos() {
        return idConceptoPuntos;
    }

    public void setIdConceptoPuntos(ConceptoPuntos idConceptoPuntos) {
        this.idConceptoPuntos = idConceptoPuntos;
    }
}
