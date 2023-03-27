package py.com.progweb.prueba.model;


import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
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

}
