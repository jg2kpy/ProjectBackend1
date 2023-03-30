package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usoPuntosDetalle")
public class UsoPuntosDetalle {
    @Id
    @Basic(optional = false)
    @Column(name = "id_usoPuntosDetalle")
    @GeneratedValue(generator = "usoPuntosDetalleSec")
    @SequenceGenerator(name = "usoPuntosDetalleSec", sequenceName = "usoPuntosDetalle_sec", allocationSize = 0)
    private Integer idUsoPuntosDetalle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usoPuntosCabecera", referencedColumnName = "id_usoPuntosCabecera")
    private UsoPuntosCabecera idUsoPuntosCabecera;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_bolsaPuntos", referencedColumnName = "id_bolsaPuntos")
    private BolsaPuntos idBolsaPuntos;

    public UsoPuntosDetalle() {

    }

    public UsoPuntosDetalle(UsoPuntosCabecera idUsoPuntosCabecera, Integer puntajeUtilizado, BolsaPuntos idBolsaPuntos) {
        this.idUsoPuntosCabecera = idUsoPuntosCabecera;
        this.puntajeUtilizado = puntajeUtilizado;
        this.idBolsaPuntos = idBolsaPuntos;
    }
}
