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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * This class is model for a User
 *
 * @version 1.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUId", query = "SELECT u FROM User u WHERE u.uId = :uId"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByVorname", query = "SELECT u FROM User u WHERE u.vorname = :vorname"),
    @NamedQuery(name = "User.findByGeburtsdatum", query = "SELECT u FROM User u WHERE u.geburtsdatum = :geburtsdatum")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "U_ID")
    private Integer uId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "VORNAME")
    private String vorname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GEBURTSDATUM")
    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUId")
    private Collection<Personal> personalCollection;
    @JoinColumn(name = "FK_A_ID", referencedColumnName = "A_ID")
    @ManyToOne(optional = false)
    private Adresse fkAId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUId")
    private Collection<Customer> customerCollection;

    public User() {
    }

    public User(Integer uId) {
        this.uId = uId;
    }

    public User(Integer uId, String name, String vorname, Date geburtsdatum) {
        this.uId = uId;
        this.name = name;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @XmlTransient
    public Collection<Personal> getPersonalCollection() {
        return personalCollection;
    }

    public void setPersonalCollection(Collection<Personal> personalCollection) {
        this.personalCollection = personalCollection;
    }

    public Adresse getFkAId() {
        return fkAId;
    }

    public void setFkAId(Adresse fkAId) {
        this.fkAId = fkAId;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uId != null ? uId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.uId == null && other.uId != null) || (this.uId != null && !this.uId.equals(other.uId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.User[ uId=" + uId + " ]";
    }
    
}
