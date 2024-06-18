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

    public Servicedetail() {
    }

    public Servicedetail(Integer sdId) {
        this.sdId = sdId;
    }

    public Servicedetail(Integer sdId, int menge) {
        this.sdId = sdId;
        this.menge = menge;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
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

    public Service getFkSId() {
        return fkSId;
    }

    public void setFkSId(Service fkSId) {
        this.fkSId = fkSId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sdId != null ? sdId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Servicedetail[ sdId=" + sdId + " ]";
    }
    
}
