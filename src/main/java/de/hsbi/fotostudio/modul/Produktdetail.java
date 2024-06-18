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
 * This class is model for a Produktdetail
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "produktdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produktdetail.findAll", query = "SELECT p FROM Produktdetail p"),
    @NamedQuery(name = "Produktdetail.findByPdId", query = "SELECT p FROM Produktdetail p WHERE p.pdId = :pdId"),
    @NamedQuery(name = "Produktdetail.findByMenge", query = "SELECT p FROM Produktdetail p WHERE p.menge = :menge")})
public class Produktdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PD_ID")
    private Integer pdId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENGE")
    private int menge;
    @JoinColumn(name = "FK_O_ID", referencedColumnName = "O_ID")
    @ManyToOne(optional = false)
    private Orders fkOId;
    @JoinColumn(name = "FK_P_ID", referencedColumnName = "P_ID")
    @ManyToOne(optional = false)
    private Produkt fkPId;

    public Produktdetail() {
    }

    public Produktdetail(Integer pdId) {
        this.pdId = pdId;
    }

    public Produktdetail(Integer pdId, int menge) {
        this.pdId = pdId;
        this.menge = menge;
    }

    public Integer getPdId() {
        return pdId;
    }

    public void setPdId(Integer pdId) {
        this.pdId = pdId;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Orders getFkOId() {
        return fkOId;
    }

    public void setFkOId(Orders fkOId) {
        this.fkOId = fkOId;
    }

    public Produkt getFkPId() {
        return fkPId;
    }

    public void setFkPId(Produkt fkPId) {
        this.fkPId = fkPId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdId != null ? pdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produktdetail)) {
            return false;
        }
        Produktdetail other = (Produktdetail) object;
        if ((this.pdId == null && other.pdId != null) || (this.pdId != null && !this.pdId.equals(other.pdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Produktdetail[ pdId=" + pdId + " ]";
    }
    
}
