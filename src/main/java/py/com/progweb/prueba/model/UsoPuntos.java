package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "usoPuntos")
public class UsoPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_usoPuntos")
    @GeneratedValue(generator="usoPuntosSec")
    @SequenceGenerator(name="usoPuntosSec",sequenceName="usoPuntos_sec",allocationSize=0)
    private Integer idUsoPuntos;

    @Basic(optional = false)
    @Column(name = "descripcion",length = 200)
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "puntos_requeridos")
    private Integer puntosRequeridos;

    public UsoPuntos(){

    }

    public Integer getIdUsoPuntos() {
        return idUsoPuntos;
    }

    public void setIdUsoPuntos(Integer idUsoPuntos) {
        this.idUsoPuntos = idUsoPuntos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
