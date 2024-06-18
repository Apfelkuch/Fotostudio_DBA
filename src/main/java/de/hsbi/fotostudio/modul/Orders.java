package de.hsbi.fotostudio.modul;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * This class is model for a Orders
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOId", query = "SELECT o FROM Orders o WHERE o.oId = :oId"),
    @NamedQuery(name = "Orders.findByZeitstempel", query = "SELECT o FROM Orders o WHERE o.zeitstempel = :zeitstempel"),
    @NamedQuery(name = "Orders.findByLieferdatum", query = "SELECT o FROM Orders o WHERE o.lieferdatum = :lieferdatum")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "O_ID")
    private Integer oId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZEITSTEMPEL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zeitstempel;
    @Column(name = "LIEFERDATUM")
    @Temporal(TemporalType.DATE)
    private Date lieferdatum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkOId")
    private Collection<Produktdetail> produktdetailCollection;
    @JoinColumn(name = "FK_C_ID", referencedColumnName = "C_ID")
    @ManyToOne(optional = false)
    private Customer fkCId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkOId")
    private Collection<Servicedetail> servicedetailCollection;

    public Orders() {
    }

    public Orders(Integer oId) {
        this.oId = oId;
    }

    public Orders(Integer oId, Date zeitstempel) {
        this.oId = oId;
        this.zeitstempel = zeitstempel;
    }

    public Integer getOId() {
        return oId;
    }

    public void setOId(Integer oId) {
        this.oId = oId;
    }

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Date getLieferdatum() {
        return lieferdatum;
    }

    public void setLieferdatum(Date lieferdatum) {
        this.lieferdatum = lieferdatum;
    }

    @XmlTransient
    public Collection<Produktdetail> getProduktdetailCollection() {
        return produktdetailCollection;
    }

    public void setProduktdetailCollection(Collection<Produktdetail> produktdetailCollection) {
        this.produktdetailCollection = produktdetailCollection;
    }

    public Customer getFkCId() {
        return fkCId;
    }

    public void setFkCId(Customer fkCId) {
        this.fkCId = fkCId;
    }

    @XmlTransient
    public Collection<Servicedetail> getServicedetailCollection() {
        return servicedetailCollection;
    }

    public void setServicedetailCollection(Collection<Servicedetail> servicedetailCollection) {
        this.servicedetailCollection = servicedetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oId != null ? oId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.oId == null && other.oId != null) || (this.oId != null && !this.oId.equals(other.oId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Orders[ oId=" + oId + " ]";
    }
    
}
