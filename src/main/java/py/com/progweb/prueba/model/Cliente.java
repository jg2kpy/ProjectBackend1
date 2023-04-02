package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Basic(optional = false)
    @Column(name = "id_cliente")
    @GeneratedValue(generator = "clienteSec")
    @SequenceGenerator(name = "clienteSec", sequenceName = "cliente_sec", allocationSize = 0)
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido", length = 50)
    private String apellido;
    @Basic(optional = false)
    @Column(name = "nro_documento", length = 50)
    private String nroDocumento;
    @Basic(optional = false)
    @Column(name = "tipo_documento", length = 50)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;
    @Basic(optional = false)
    @Column(name = "email", length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "telefono", length = 50)
    private String telefono;
    @Basic(optional = true)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Cliente() {

    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nroDocumento='" + nroDocumento + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
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

    public String getTipoDocumento() {
        return tipoDocumento;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
