package py.com.progweb.prueba.model;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Basic(optional = false)
    @Column(name = "id_cliente")
    @GeneratedValue(generator="clienteSec")
    @SequenceGenerator(name="clienteSec",sequenceName="cliente_sec",allocationSize=0)
    private Integer idCliente;

    @Basic(optional = false)
    @Column(name = "nombre",length = 50)
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido",length = 50)
    private String apellido;

    @Basic(optional = false)
    @Column(name = "nro_documento",length = 50)
    private String nroDocumento;

    @Basic(optional = false)
    @Column(name = "tipo_documento",length = 50)
    private String tipoDocumento;

    @Basic(optional = false)
    @Column(name = "nacionalidad",length = 50)
    private String nacionalidad;

    @Basic(optional = false)
    @Column(name = "email",length = 50)
    private String email;

    @Basic(optional = false)
    @Column(name = "teléfono",length = 50)
    private String teléfono;

    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Cliente(){

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
