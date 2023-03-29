package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "usoPuntosCabecera")
public class UsoPuntosCabecera {
    @Id
    @Basic(optional = false)
    @Column(name = "id_usoPuntosCabecera")
    @GeneratedValue(generator="usoPuntosCabeceraSec")
    @SequenceGenerator(name="usoPuntosCabeceraSec",sequenceName="usoPuntosCabecera_sec",allocationSize=0)
    private Integer idUsoPuntosCabecera;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente",referencedColumnName = "id_cliente")
    private Cliente idCliente;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conceptoPuntos",referencedColumnName = "id_conceptoPuntos")
    private ConceptoPuntos idConceptoPuntos;

    public UsoPuntosCabecera(){

    }
}
