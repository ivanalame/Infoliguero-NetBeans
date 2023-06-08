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
@Table(name = "preguntap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntap.findAll", query = "SELECT p FROM Preguntap p")
    , @NamedQuery(name = "Preguntap.findById", query = "SELECT p FROM Preguntap p WHERE p.id = :id")
    , @NamedQuery(name = "Preguntap.findByTexto", query = "SELECT p FROM Preguntap p WHERE p.texto = :texto")})
public class Preguntap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "texto")
    private String texto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<Respuestap> respuestapList;

    public Preguntap() {
    }

    public Preguntap(Integer id) {
        this.id = id;
    }

    public Preguntap(Integer id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @XmlTransient
    public List<Respuestap> getRespuestapList() {
        return respuestapList;
    }

    public void setRespuestapList(List<Respuestap> respuestapList) {
        this.respuestapList = respuestapList;
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
        if (!(object instanceof Preguntap)) {
            return false;
        }
        Preguntap other = (Preguntap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Preguntap[ id=" + id + " ]";
    }
    
}
