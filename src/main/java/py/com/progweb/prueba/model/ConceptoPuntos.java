package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "conceptoPuntos")
public class ConceptoPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_conceptoPuntos")
    @GeneratedValue(generator="conceptoPuntosSec")
    @SequenceGenerator(name="conceptoPuntosSec",sequenceName="conceptoPuntos_sec",allocationSize=0)
    private Integer idConceptoPuntos;

    @Basic(optional = false)
    @Column(name = "descripcion",length = 200)
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "puntos_requeridos")
    private Integer puntosRequeridos;

    public ConceptoPuntos(){

    }
}
