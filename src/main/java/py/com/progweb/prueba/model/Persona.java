package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @Basic(optional = false)
    @Column(name = "id_persona")
    @GeneratedValue(generator="personaSec")
    @SequenceGenerator(name="personaSec",sequenceName="persona_sec",allocationSize=0)
    private Integer idPersona;
    @Basic(optional = false)
    @Column(name = "nombre",length = 50)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido",length = 50)
    private String apellido;

    @OneToMany(mappedBy = "idPersona",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Agenda> listaAgendas;

    @Transient
    private List<Agenda> listaAgendasJson;

    public Persona(){

    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Agenda> getListaAgendas() {
        return listaAgendas;
    }

    public void setListaAgendas(List<Agenda> listaAgendas) {
        this.listaAgendas = listaAgendas;
    }

    public List<Agenda> getListaAgendasJson() {
        return listaAgendasJson;
    }

    public void setListaAgendasJson(List<Agenda> listaAgendasJson) {
        this.listaAgendasJson = listaAgendasJson;
    }
}
