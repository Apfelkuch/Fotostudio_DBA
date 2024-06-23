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

    /**
     * Creates instance of Service
     */
    public Personal() {
    }

    /**
     * Creates instance of Service
     * 
     * @param psnId the psnId parameter for the new instance
     */
    public Personal(Integer psnId) {
        this.psnId = psnId;
    }

    /**
     * Creates instance of Service
     * 
     * @param psnId the psnId parameter for the new instance
     * @param aufgabe the aufgabe parameter for the new instance
     */
    public Personal(Integer psnId, String aufgabe) {
        this.psnId = psnId;
        this.aufgabe = aufgabe;
    }

    /**
     * Get Value of psnId
     * 
     * @return the value of psnId
     */
    public Integer getPsnId() {
        return psnId;
    }

    /**
     * Set Value of psnId
     * 
     * @param psnId the new value of psnId
     */
    public void setPsnId(Integer psnId) {
        this.psnId = psnId;
    }

    /**
     * Get Value of aufgabe
     * 
     * @return the value of aufgabe
     */
    public String getAufgabe() {
        return aufgabe;
    }

    /**
     * Set Value of aufgabe
     * 
     * @param aufgabe the new value of aufgabe
     */
    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    /**
     * Get Value of fkUId
     * 
     * @return the value of fkUId
     */
    public User getFkUId() {
        return fkUId;
    }

    /**
     * Set Value of fkUId
     * 
     * @param fkUId the new value of fkUId
     */
    public void setFkUId(User fkUId) {
        this.fkUId = fkUId;
    }

    /**
     * Get Value of servicepersonalCollection
     * 
     * @return the value of servicepersonalCollection
     */
    @XmlTransient
    public Collection<Servicepersonal> getServicepersonalCollection() {
        return servicepersonalCollection;
    }

    /**
     * Set Value of servicepersonalCollection
     * 
     * @param servicepersonalCollection the new value of servicepersonalCollection
     */
    public void setServicepersonalCollection(Collection<Servicepersonal> servicepersonalCollection) {
        this.servicepersonalCollection = servicepersonalCollection;
    }

    /**
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psnId != null ? psnId.hashCode() : 0);
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
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.psnId == null && other.psnId != null) || (this.psnId != null && !this.psnId.equals(other.psnId))) {
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
        return "de.hsbi.fotostudio.modul.Personal[ psnId=" + psnId + " ]";
    }
    
}
