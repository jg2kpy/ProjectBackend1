package py.com.progweb.prueba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
