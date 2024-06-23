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
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * This class is model for a Servicedetail
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "servicedetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicedetail.findAll", query = "SELECT s FROM Servicedetail s"),
    @NamedQuery(name = "Servicedetail.findBySdId", query = "SELECT s FROM Servicedetail s WHERE s.sdId = :sdId"),
    @NamedQuery(name = "Servicedetail.findByMenge", query = "SELECT s FROM Servicedetail s WHERE s.menge = :menge")})
public class Servicedetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SD_ID")
    private Integer sdId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENGE")
    private int menge;
    @JoinColumn(name = "FK_O_ID", referencedColumnName = "O_ID")
    @ManyToOne(optional = false)
    private Orders fkOId;
    @JoinColumn(name = "FK_S_ID", referencedColumnName = "S_ID")
    @ManyToOne(optional = false)
    private Service fkSId;

    /**
     * Creates instance of Service
     */
    public Servicedetail() {
    }

    /**
     * Creates instance of Service
     * 
     * @param sdId the sdId parameter for the new instance
     */
    public Servicedetail(Integer sdId) {
        this.sdId = sdId;
    }

    /**
     * Creates instance of Service
     * 
     * @param sdId the sdId parameter for the new instance
     * @param menge the menge parameter for the new instance
     */
    public Servicedetail(Integer sdId, int menge) {
        this.sdId = sdId;
        this.menge = menge;
    }

    /**
     * Get Value of sdId
     * 
     * @return the value of sdId
     */
    public Integer getSdId() {
        return sdId;
    }

    /**
     * Set Value of sdId
     * 
     * @param sdId the new value of sdId
     */
    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    /**
     * Get Value of menge
     * 
     * @return the value of menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Set Value of menge
     * 
     * @param menge the new value of menge
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Get Value of fkOId
     * 
     * @return the value of fkOId
     */
    public Orders getFkOId() {
        return fkOId;
    }

    /**
     * Set Value of fkOId
     * 
     * @param fkOId the new value of fkOId
     */
    public void setFkOId(Orders fkOId) {
        this.fkOId = fkOId;
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
        hash += (sdId != null ? sdId.hashCode() : 0);
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
        if (!(object instanceof Servicedetail)) {
            return false;
        }
        Servicedetail other = (Servicedetail) object;
        if ((this.sdId == null && other.sdId != null) || (this.sdId != null && !this.sdId.equals(other.sdId))) {
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
        return "de.hsbi.fotostudio.modul.Servicedetail[ sdId=" + sdId + " ]";
    }
    
}
