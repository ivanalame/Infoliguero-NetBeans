/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iván Juárez
 */
@Entity
@Table(name = "equipoit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipoit.findAll", query = "SELECT e FROM Equipoit e")
    , @NamedQuery(name = "Equipoit.findById", query = "SELECT e FROM Equipoit e WHERE e.id = :id")
    , @NamedQuery(name = "Equipoit.findByNombre", query = "SELECT e FROM Equipoit e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Equipoit.findByImagen", query = "SELECT e FROM Equipoit e WHERE e.imagen = :imagen")})
public class Equipoit implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
    private List<Jugadorit> jugadoritList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "imagen")
    private String imagen;

    public Equipoit() {
    }

    public Equipoit(Integer id) {
        this.id = id;
    }

    public Equipoit(Integer id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
        if (!(object instanceof Equipoit)) {
            return false;
        }
        Equipoit other = (Equipoit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipoit[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Jugadorit> getJugadoritList() {
        return jugadoritList;
    }

    public void setJugadoritList(List<Jugadorit> jugadoritList) {
        this.jugadoritList = jugadoritList;
    }
    
}
