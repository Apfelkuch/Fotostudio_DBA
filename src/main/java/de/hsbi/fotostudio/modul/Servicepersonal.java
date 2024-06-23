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

    /**
     * Creates instance of Service
     */
    public Servicepersonal() {
    }

    /**
     * Creates instance of Service
     * 
     * @param spId the spId parameter for the new instance
     */
    public Servicepersonal(Integer spId) {
        this.spId = spId;
    }

    /**
     * Get Value of spId
     * 
     * @return the value of spId
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * Set Value of spId
     * 
     * @param spId the new value of spId
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * Get Value of fkPsnId
     * 
     * @return the value of fkPsnId
     */
    public Personal getFkPsnId() {
        return fkPsnId;
    }

    /**
     * Set Value of fkPsnId
     * 
     * @param fkPsnId the new value of fkPsnId
     */
    public void setFkPsnId(Personal fkPsnId) {
        this.fkPsnId = fkPsnId;
    }

    /**
     * Get Value of fkSId
     * 
     * @return the value of fkSId
     */
    public Service getFkSId() {
        return fkSId;
    }

    /**
     * Set Value of fkSId
     * 
     * @param fkSId the new value of fkSId
     */
    public void setFkSId(Service fkSId) {
        this.fkSId = fkSId;
    }

    /**
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spId != null ? spId.hashCode() : 0);
        return hash;
    }

    /**
     * Compares a object to this instance using the id.
     * 
     * @param object the objekt this instance is compared against
     * @return true if the object and this instance are the equal, otherwise false
     */
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

    /**
     * Converts the Obeject into a String, showing the id
     * 
     * @return the String which represants the Object data
     */
    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Servicepersonal[ spId=" + spId + " ]";
    }
    
}
