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
 * This class is model for a Zubehör
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "zubeh\u00f6r")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zubeh\u00f6r.findAll", query = "SELECT z FROM Zubeh\u00f6r z"),
    @NamedQuery(name = "Zubeh\u00f6r.findByZId", query = "SELECT z FROM Zubeh\u00f6r z WHERE z.zId = :zId"),
    @NamedQuery(name = "Zubeh\u00f6r.findByArtikelname", query = "SELECT z FROM Zubeh\u00f6r z WHERE z.artikelname = :artikelname")})
public class Zubehör implements Serializable {

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

    public Zubehör() {
    }

    public Zubehör(Integer zId) {
        this.zId = zId;
    }

    public Zubehör(Integer zId, String artikelname, String artikelbeschreibung) {
        this.zId = zId;
        this.artikelname = artikelname;
        this.artikelbeschreibung = artikelbeschreibung;
    }

    public Integer getZId() {
        return zId;
    }

    public void setZId(Integer zId) {
        this.zId = zId;
    }

    public String getArtikelname() {
        return artikelname;
    }

    public void setArtikelname(String artikelname) {
        this.artikelname = artikelname;
    }

    public String getArtikelbeschreibung() {
        return artikelbeschreibung;
    }

    public void setArtikelbeschreibung(String artikelbeschreibung) {
        this.artikelbeschreibung = artikelbeschreibung;
    }

    @XmlTransient
    public Collection<Lieferumfang> getLieferumfangCollection() {
        return lieferumfangCollection;
    }

    public void setLieferumfangCollection(Collection<Lieferumfang> lieferumfangCollection) {
        this.lieferumfangCollection = lieferumfangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zId != null ? zId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zubehör)) {
            return false;
        }
        Zubehör other = (Zubehör) object;
        if ((this.zId == null && other.zId != null) || (this.zId != null && !this.zId.equals(other.zId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Zubeh\u00f6r[ zId=" + zId + " ]";
    }
    
}
