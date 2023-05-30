/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iván Juárez
 */
@Entity
@Table(name = "jugadorit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugadorit.findAll", query = "SELECT j FROM Jugadorit j")
    , @NamedQuery(name = "Jugadorit.findById", query = "SELECT j FROM Jugadorit j WHERE j.id = :id")
    , @NamedQuery(name = "Jugadorit.findByNombre", query = "SELECT j FROM Jugadorit j WHERE j.nombre = :nombre")
    , @NamedQuery(name = "Jugadorit.findByEdad", query = "SELECT j FROM Jugadorit j WHERE j.edad = :edad")
    , @NamedQuery(name = "Jugadorit.findByPosicion", query = "SELECT j FROM Jugadorit j WHERE j.posicion = :posicion")
    , @NamedQuery(name = "Jugadorit.findByDorsal", query = "SELECT j FROM Jugadorit j WHERE j.dorsal = :dorsal")
    , @NamedQuery(name = "Jugadorit.findByFechaNacimiento", query = "SELECT j FROM Jugadorit j WHERE j.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Jugadorit.findByPais", query = "SELECT j FROM Jugadorit j WHERE j.pais = :pais")
    , @NamedQuery(name = "Jugadorit.findByFoto", query = "SELECT j FROM Jugadorit j WHERE j.foto = :foto")})
public class Jugadorit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Edad")
    private String edad;
    @Basic(optional = false)
    @Column(name = "Posicion")
    private String posicion;
    @Basic(optional = false)
    @Column(name = "Dorsal")
    private String dorsal;
    @Basic(optional = false)
    @Column(name = "Fecha_Nacimiento")
    private String fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "Pais")
    private String pais;
    @Basic(optional = false)
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equipoit idEquipo;

    public Jugadorit() {
    }

    public Jugadorit(Integer id) {
        this.id = id;
    }

    public Jugadorit(Integer id, String nombre, String edad, String posicion, String dorsal, String fechaNacimiento, String pais, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Equipoit getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipoit idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugadorit)) {
            return false;
        }
        Jugadorit other = (Jugadorit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Jugadorit[ id=" + id + " ]";
    }
    
}
