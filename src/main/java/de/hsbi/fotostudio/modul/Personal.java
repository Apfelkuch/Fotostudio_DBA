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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * This class is model for a Personal
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByPsnId", query = "SELECT p FROM Personal p WHERE p.psnId = :psnId"),
    @NamedQuery(name = "Personal.findByAufgabe", query = "SELECT p FROM Personal p WHERE p.aufgabe = :aufgabe")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PSN_ID")
    private Integer psnId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "AUFGABE")
    private String aufgabe;
    @JoinColumn(name = "FK_U_ID", referencedColumnName = "U_ID")
    @ManyToOne(optional = false)
    private User fkUId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPsnId")
    private Collection<Servicepersonal> servicepersonalCollection;

    public Personal() {
    }

    public Personal(Integer psnId) {
        this.psnId = psnId;
    }

    public Personal(Integer psnId, String aufgabe) {
        this.psnId = psnId;
        this.aufgabe = aufgabe;
    }

    public Integer getPsnId() {
        return psnId;
    }

    public void setPsnId(Integer psnId) {
        this.psnId = psnId;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public User getFkUId() {
        return fkUId;
    }

    public void setFkUId(User fkUId) {
        this.fkUId = fkUId;
    }

    @XmlTransient
    public Collection<Servicepersonal> getServicepersonalCollection() {
        return servicepersonalCollection;
    }

    public void setServicepersonalCollection(Collection<Servicepersonal> servicepersonalCollection) {
        this.servicepersonalCollection = servicepersonalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psnId != null ? psnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.psnId == null && other.psnId != null) || (this.psnId != null && !this.psnId.equals(other.psnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Personal[ psnId=" + psnId + " ]";
    }
    
}
