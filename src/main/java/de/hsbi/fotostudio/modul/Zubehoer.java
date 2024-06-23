package de.hsbi.fotostudio.modul;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
 * This class is model for a Zubehoer
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "zubehoer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zubehoer.findAll", query = "SELECT z FROM Zubehoer z"),
    @NamedQuery(name = "Zubehoer.findByZId", query = "SELECT z FROM Zubehoer z WHERE z.zId = :zId"),
    @NamedQuery(name = "Zubehoer.findByArtikelname", query = "SELECT z FROM Zubehoer z WHERE z.artikelname = :artikelname")})
public class Zubehoer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Z_ID")
    private Integer zId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ARTIKELNAME")
    private String artikelname;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "ARTIKELBESCHREIBUNG")
    private String artikelbeschreibung;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkZId")
    private Collection<Lieferumfang> lieferumfangCollection;

    /**
     * Creates instance of Service
     */
    public Zubehoer() {
    }

    /**
     * Creates instance of Service
     * 
     * @param zId the zId parameter for the new instance
     */
    public Zubehoer(Integer zId) {
        this.zId = zId;
    }

    /**
     * Creates instance of Service
     * 
     * @param zId the zId parameter for the new instance
     * @param artikelname the artikelname parameter for the new instance
     * @param artikelbeschreibung the artikelbeschreibung parameter for the new instance
     */
    public Zubehoer(Integer zId, String artikelname, String artikelbeschreibung) {
        this.zId = zId;
        this.artikelname = artikelname;
        this.artikelbeschreibung = artikelbeschreibung;
    }

    /**
     * Get Value of zId
     * 
     * @return the value of zId
     */
    public Integer getZId() {
        return zId;
    }

    /**
     * Set Value of zId
     * 
     * @param zId the new value of zId
     */
    public void setZId(Integer zId) {
        this.zId = zId;
    }

    /**
     * Get Value of artikelname
     * 
     * @return the value of artikelname
     */
    public String getArtikelname() {
        return artikelname;
    }

    /**
     * Set Value of artikelname
     * 
     * @param artikelname the new value of artikelname
     */
    public void setArtikelname(String artikelname) {
        this.artikelname = artikelname;
    }

    /**
     * Get Value of artikelbeschreibung
     * 
     * @return the value of artikelbeschreibung
     */
    public String getArtikelbeschreibung() {
        return artikelbeschreibung;
    }

    /**
     * Set Value of artikelbeschreibung
     * 
     * @param artikelbeschreibung the new value of artikelbeschreibung
     */
    public void setArtikelbeschreibung(String artikelbeschreibung) {
        this.artikelbeschreibung = artikelbeschreibung;
    }

    /**
     * Get Value of lieferumfangCollection
     * 
     * @return the value of lieferumfangCollection
     */
    @XmlTransient
    public Collection<Lieferumfang> getLieferumfangCollection() {
        return lieferumfangCollection;
    }

    /**
     * Set Value of lieferumfangCollection
     * 
     * @param lieferumfangCollection the new value of lieferumfangCollection
     */
    public void setLieferumfangCollection(Collection<Lieferumfang> lieferumfangCollection) {
        this.lieferumfangCollection = lieferumfangCollection;
    }

    /**
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zId != null ? zId.hashCode() : 0);
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
        if (!(object instanceof Zubehoer)) {
            return false;
        }
        Zubehoer other = (Zubehoer) object;
        if ((this.zId == null && other.zId != null) || (this.zId != null && !this.zId.equals(other.zId))) {
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
        return "de.hsbi.fotostudio.modul.Zubehoer[ zId=" + zId + " ]";
    }
    
}
