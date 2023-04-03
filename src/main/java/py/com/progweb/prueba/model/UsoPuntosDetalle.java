package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
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

    // Getter y setter para idUsoPuntosDetalle
    public Integer getIdUsoPuntosDetalle() {
        return idUsoPuntosDetalle;
    }

    public void setIdUsoPuntosDetalle(Integer idUsoPuntosDetalle) {
        this.idUsoPuntosDetalle = idUsoPuntosDetalle;
    }

    // Getter y setter para idUsoPuntosCabecera
    public UsoPuntosCabecera getIdUsoPuntosCabecera() {
        return idUsoPuntosCabecera;
    }

    public void setIdUsoPuntosCabecera(UsoPuntosCabecera idUsoPuntosCabecera) {
        this.idUsoPuntosCabecera = idUsoPuntosCabecera;
    }

    // Getter y setter para puntajeUtilizado
    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    // Getter y setter para idBolsaPuntos
    public BolsaPuntos getIdBolsaPuntos() {
        return idBolsaPuntos;
    }

    public void setIdBolsaPuntos(BolsaPuntos idBolsaPuntos) {
        this.idBolsaPuntos = idBolsaPuntos;
    }
}
