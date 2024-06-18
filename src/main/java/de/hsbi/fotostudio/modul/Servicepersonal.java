package de.hsbi.fotostudio.modul;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * This class is model for a Servicepersonal
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "servicepersonal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicepersonal.findAll", query = "SELECT s FROM Servicepersonal s"),
    @NamedQuery(name = "Servicepersonal.findBySpId", query = "SELECT s FROM Servicepersonal s WHERE s.spId = :spId")})
public class Servicepersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SP_ID")
    private Integer spId;
    @JoinColumn(name = "FK_PSN_ID", referencedColumnName = "PSN_ID")
    @ManyToOne(optional = false)
    private Personal fkPsnId;
    @JoinColumn(name = "FK_S_ID", referencedColumnName = "S_ID")
    @ManyToOne(optional = false)
    private Service fkSId;

    public Servicepersonal() {
    }

    public Servicepersonal(Integer spId) {
        this.spId = spId;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Personal getFkPsnId() {
        return fkPsnId;
    }

    public void setFkPsnId(Personal fkPsnId) {
        this.fkPsnId = fkPsnId;
    }

    public Service getFkSId() {
        return fkSId;
    }

    public void setFkSId(Service fkSId) {
        this.fkSId = fkSId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spId != null ? spId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicepersonal)) {
            return false;
        }
        Servicepersonal other = (Servicepersonal) object;
        if ((this.spId == null && other.spId != null) || (this.spId != null && !this.spId.equals(other.spId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Servicepersonal[ spId=" + spId + " ]";
    }
    
}
