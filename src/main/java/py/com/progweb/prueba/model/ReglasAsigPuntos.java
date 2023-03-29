package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reglasasigpuntos")
public class ReglasAsigPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_reglasasigpuntos")
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

    // Métodos getter y setter para idReglasAsigPuntos
    public Integer getIdReglasAsigPuntos() {
        return idReglasAsigPuntos;
    }

    public void setIdReglasAsigPuntos(Integer idReglasAsigPuntos) {
        this.idReglasAsigPuntos = idReglasAsigPuntos;
    }

    // Métodos getter y setter para limiteInferior
    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    // Métodos getter y setter para limiteSuperior
    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    // Métodos getter y setter para montoEquivalencia
    public Integer getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }

    public ReglasAsigPuntos(){

    }
}
