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
    private Zubehör fkZId;

    public Lieferumfang() {
    }

    public Lieferumfang(Integer lId) {
        this.lId = lId;
    }

    public Lieferumfang(Integer lId, long menge) {
        this.lId = lId;
        this.menge = menge;
    }

    public Integer getLId() {
        return lId;
    }

    public void setLId(Integer lId) {
        this.lId = lId;
    }

    public long getMenge() {
        return menge;
    }

    public void setMenge(long menge) {
        this.menge = menge;
    }

    public Produkt getFkPId() {
        return fkPId;
    }

    public void setFkPId(Produkt fkPId) {
        this.fkPId = fkPId;
    }

    public Zubehör getFkZId() {
        return fkZId;
    }

    public void setFkZId(Zubehör fkZId) {
        this.fkZId = fkZId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lId != null ? lId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Lieferumfang[ lId=" + lId + " ]";
    }
    
}
