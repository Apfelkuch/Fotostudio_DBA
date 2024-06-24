package de.hsbi.fotostudio.modul;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * This class is model for a Adresse
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "adresse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByAId", query = "SELECT a FROM Adresse a WHERE a.aId = :aId"),
    @NamedQuery(name = "Adresse.findByStrasse", query = "SELECT a FROM Adresse a WHERE a.strasse = :strasse"),
    @NamedQuery(name = "Adresse.findByHausnr", query = "SELECT a FROM Adresse a WHERE a.hausnr = :hausnr"),
    @NamedQuery(name = "Adresse.findByPlz", query = "SELECT a FROM Adresse a WHERE a.plz = :plz"),
    @NamedQuery(name = "Adresse.findByOrt", query = "SELECT a FROM Adresse a WHERE a.ort = :ort"),
    @NamedQuery(name = "Adresse.findByLand", query = "SELECT a FROM Adresse a WHERE a.land = :land")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "A_ID")
    private Integer aId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STRASSE")
    private String strasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAUSNR")
    private int hausnr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLZ")
    private int plz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ORT")
    private String ort;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LAND")
    private String land;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAId")
    private Collection<User> userCollection;

    /**
     * Creates instance of Service
     */
    public Adresse() {
    }

    /**
     * Creates instance of Service
     * 
     * @param aId the aId parameter for the new instance
     */
    public Adresse(Integer aId) {
        this.aId = aId;
    }

    /**
     * Creates instance of Service
     * 
     * @param aId the aId parameter for the new instance
     * @param strasse the strasse parameter for the new instance
     * @param hausnr the hausnr parameter for the new instance
     * @param plz the plz parameter for the new instance
     * @param ort the ort parameter for the new instance
     * @param land the land parameter for the new instance
     */
    public Adresse(Integer aId, String strasse, int hausnr, int plz, String ort, String land) {
        this.aId = aId;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
    }
    
    /**
     * Get Value of aId
     * 
     * @return the value of aId
     */
    public Integer getAId() {
        return aId;
    }
    
    /**
     * Set Value of aId
     * 
     * @param aId the new value of aId
     */
    public void setAId(Integer aId) {
        this.aId = aId;
    }
    
    /**
     * Get Value of strasse
     * 
     * @return the value of strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Set Value of strasse
     * 
     * @param strasse the new value of strasse
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * Get Value of hausnr
     * 
     * @return the value of hausnr
     */
    public int getHausnr() {
        return hausnr;
    }

    /**
     * Set Value of hausnr
     * 
     * @param hausnr the new value of hausnr
     */
    public void setHausnr(int hausnr) {
        this.hausnr = hausnr;
    }

    /**
     * Get Value of plz
     * 
     * @return the value of plz
     */
    public int getPlz() {
        return plz;
    }

    /**
     * Set Value of plz
     * 
     * @param plz the new value of plz
     */
    public void setPlz(int plz) {
        this.plz = plz;
    }

    /**
     * Get Value of ort
     * 
     * @return the value of ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Set Value of ort
     * 
     * @param ort the new value of ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * Get Value of land
     * 
     * @return the value of land
     */
    public String getLand() {
        return land;
    }

    /**
     * Set Value of land
     * 
     * @param land the new value of land
     */
    public void setLand(String land) {
        this.land = land;
    }

    /**
     * Get Value of userCollection
     * 
     * @return the value of userCollection
     */
    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    /**
     * Set Value of userCollection
     * 
     * @param userCollection the new value of userCollection
     */
    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    /**
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aId != null ? aId.hashCode() : 0);
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.aId == null && other.aId != null) || (this.aId != null && !this.aId.equals(other.aId))) {
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
        return "de.hsbi.fotostudio.modul.Adresse[ aId=" + aId + " ]";
    }
    
}
