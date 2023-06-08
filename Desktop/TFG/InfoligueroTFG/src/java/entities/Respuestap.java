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
@Table(name = "respuestap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestap.findAll", query = "SELECT r FROM Respuestap r")
    , @NamedQuery(name = "Respuestap.findById", query = "SELECT r FROM Respuestap r WHERE r.id = :id")
    , @NamedQuery(name = "Respuestap.findByTexto", query = "SELECT r FROM Respuestap r WHERE r.texto = :texto")
    , @NamedQuery(name = "Respuestap.findByCorrecta", query = "SELECT r FROM Respuestap r WHERE r.correcta = :correcta")})
public class Respuestap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "correcta")
    private boolean correcta;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Preguntap idPregunta;

    public Respuestap() {
    }

    public Respuestap(Integer id) {
        this.id = id;
    }

    public Respuestap(Integer id, String texto, boolean correcta) {
        this.id = id;
        this.texto = texto;
        this.correcta = correcta;
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

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public Preguntap getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Preguntap idPregunta) {
        this.idPregunta = idPregunta;
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
        if (!(object instanceof Respuestap)) {
            return false;
        }
        Respuestap other = (Respuestap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Respuestap[ id=" + id + " ]";
    }
    
}
