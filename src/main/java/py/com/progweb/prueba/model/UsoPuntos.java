package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
