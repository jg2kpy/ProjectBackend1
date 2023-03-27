package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "reglasAsigPuntos")
public class ReglasAsigPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_reglasAsigPuntos")
    @GeneratedValue(generator="reglasAsigPuntosSec")
    @SequenceGenerator(name="reglasAsigPuntosSec",sequenceName="reglasAsigPuntos_sec",allocationSize=0)
    private Integer idReglasAsigPuntos;

    @Basic(optional = false)
    @Column(name = "limite_inferior")
    private Integer limiteInferior;

    @Basic(optional = false)
    @Column(name = "limite_superior")
    private Integer limiteSuperior;

    @Basic(optional = false)
    @Column(name = "monto_equivalencia")
    private Integer montoEquivalencia;

    public ReglasAsigPuntos(){

    }

    public Integer getIdReglasAsigPuntos() {
        return idReglasAsigPuntos;
    }

    public void setIdReglasAsigPuntos(Integer idReglasAsigPuntos) {
        this.idReglasAsigPuntos = idReglasAsigPuntos;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getMontoEquivalencia() {
        return limiteSuperior;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }
}
