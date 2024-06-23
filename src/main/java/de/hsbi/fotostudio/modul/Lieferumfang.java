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
 * This class is model for a Lieferumfang
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "lieferumfang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lieferumfang.findAll", query = "SELECT l FROM Lieferumfang l"),
    @NamedQuery(name = "Lieferumfang.findByLId", query = "SELECT l FROM Lieferumfang l WHERE l.lId = :lId"),
    @NamedQuery(name = "Lieferumfang.findByMenge", query = "SELECT l FROM Lieferumfang l WHERE l.menge = :menge")})
public class Lieferumfang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "L_ID")
    private Integer lId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENGE")
    private long menge;
    @JoinColumn(name = "FK_P_ID", referencedColumnName = "P_ID")
    @ManyToOne(optional = false)
    private Produkt fkPId;
    @JoinColumn(name = "FK_Z_ID", referencedColumnName = "Z_ID")
    @ManyToOne(optional = false)
    private Zubehoer fkZId;

    /**
     * Creates instance of Service
     */
    public Lieferumfang() {
    }

    /**
     * Creates instance of Service
     * 
     * @param lId the lId parameter for the new instance
     */
    public Lieferumfang(Integer lId) {
        this.lId = lId;
    }

    /**
     * Creates instance of Service
     * 
     * @param lId the lId parameter for the new instance
     * @param menge the menge parameter for the new instance
     */
    public Lieferumfang(Integer lId, long menge) {
        this.lId = lId;
        this.menge = menge;
    }

    /**
     * Get Value of lId
     * 
     * @return the value of lId
     */
    public Integer getLId() {
        return lId;
    }

    /**
     * Set Value of lId
     * 
     * @param lId the new value of lId
     */
    public void setLId(Integer lId) {
        this.lId = lId;
    }

    /**
     * Get Value of menge
     * 
     * @return the value of menge
     */
    public long getMenge() {
        return menge;
    }

    /**
     * Set Value of menge
     * 
     * @param menge the new value of menge
     */
    public void setMenge(long menge) {
        this.menge = menge;
    }

    /**
     * Get Value of fkPId
     * 
     * @return the value of fkPId
     */
    public Produkt getFkPId() {
        return fkPId;
    }

    /**
     * Set Value of fkPId
     * 
     * @param fkPId the new value of fkPId
     */
    public void setFkPId(Produkt fkPId) {
        this.fkPId = fkPId;
    }

    /**
     * Get Value of fkZId
     * 
     * @return the value of fkZId
     */
    public Zubehoer getFkZId() {
        return fkZId;
    }

    /**
     * Set Value of fkZId
     * 
     * @param fkZId the new value of fkZId
     */
    public void setFkZId(Zubehoer fkZId) {
        this.fkZId = fkZId;
    }

    /**
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lId != null ? lId.hashCode() : 0);
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
        if (!(object instanceof Lieferumfang)) {
            return false;
        }
        Lieferumfang other = (Lieferumfang) object;
        if ((this.lId == null && other.lId != null) || (this.lId != null && !this.lId.equals(other.lId))) {
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
        return "de.hsbi.fotostudio.modul.Lieferumfang[ lId=" + lId + " ]";
    }
    
}
