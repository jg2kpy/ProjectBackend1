package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "conceptopuntos")
public class ConceptoPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_conceptopuntos")
    @GeneratedValue(generator="conceptoPuntosSec")
    @SequenceGenerator(name="conceptoPuntosSec",sequenceName="conceptoPuntos_sec",allocationSize=0)
    private Integer idConceptoPuntos;

    @Basic(optional = false)
    @Column(name = "descripcion",length = 200)
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "puntos_requeridos")
    private Integer puntosRequeridos;

    // Métodos getter y setter para idConceptoPuntos
    public Integer getIdConceptoPuntos() {
        return idConceptoPuntos;
    }

    public void setIdConceptoPuntos(Integer idConceptoPuntos) {
        this.idConceptoPuntos = idConceptoPuntos;
    }

    // Métodos getter y setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para puntosRequeridos
    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    public ConceptoPuntos(){

    }
}
