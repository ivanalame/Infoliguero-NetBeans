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
@Table(name = "equipobu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipobu.findAll", query = "SELECT e FROM Equipobu e")
    , @NamedQuery(name = "Equipobu.findById", query = "SELECT e FROM Equipobu e WHERE e.id = :id")
    , @NamedQuery(name = "Equipobu.findByNombre", query = "SELECT e FROM Equipobu e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Equipobu.findByImagen", query = "SELECT e FROM Equipobu e WHERE e.imagen = :imagen")})
public class Equipobu implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
    private List<Jugadorbu> jugadorbuList;

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

    public Equipobu() {
    }

    public Equipobu(Integer id) {
        this.id = id;
    }

    public Equipobu(Integer id, String nombre, String imagen) {
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
        if (!(object instanceof Equipobu)) {
            return false;
        }
        Equipobu other = (Equipobu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipobu[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Jugadorbu> getJugadorbuList() {
        return jugadorbuList;
    }

    public void setJugadorbuList(List<Jugadorbu> jugadorbuList) {
        this.jugadorbuList = jugadorbuList;
    }
    
}
