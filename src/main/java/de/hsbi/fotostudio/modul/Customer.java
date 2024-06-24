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
 * This class is model for a Customer
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCId", query = "SELECT c FROM Customer c WHERE c.cId = :cId"),
    @NamedQuery(name = "Customer.findByBenutzername", query = "SELECT c FROM Customer c WHERE c.benutzername = :benutzername"),
    @NamedQuery(name = "Customer.findByPasswort", query = "SELECT c FROM Customer c WHERE c.passwort = :passwort"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByRolle", query = "SELECT c FROM Customer c WHERE c.rolle = :rolle"),
    @NamedQuery(name = "Customer.findByZeitstempel", query = "SELECT c FROM Customer c WHERE c.zeitstempel = :zeitstempel"),
    @NamedQuery(name = "Customer.findByIstmitarbeiter", query = "SELECT c FROM Customer c WHERE c.istmitarbeiter = :istmitarbeiter"),
    @NamedQuery(name = "Customer.exists", query = "SELECT COUNT(c.cId) FROM Customer c WHERE c.benutzername = :benutzername"),
    @NamedQuery(name = "Customer.login", query = "SELECT c FROM Customer c WHERE c.benutzername = :benutztername AND c.passwort = :passwort")
})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "C_ID")
    private Integer cId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BENUTZERNAME")
    private String benutzername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORT")
    private String passwort;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLLE")
    private int rolle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZEITSTEMPEL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zeitstempel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISTMITARBEITER")
    private Boolean istmitarbeiter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCId")
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "FK_U_ID", referencedColumnName = "U_ID")
    @ManyToOne(optional = false)
    private User fkUId;

    /**
     * Creates instance of Service
     */
    public Customer() {
    }

    /**
     * Creates instance of Service
     * 
     * @param cId the cId parameter for the new instance
     */
    public Customer(Integer cId) {
        this.cId = cId;
    }

    /**
     * Creates instance of Service
     * 
     * @param cId the id parameter for the new instance
     * @param benutzername the benutzername parameter for the new instance
     * @param passwort the passwort parameter for the new instance
     * @param email the email parameter for the new instance
     * @param rolle the rolle parameter for the new instance
     * @param zeitstempel the zeitstempel parameter for the new instance
     * @param istmitarbeiter the istmitarbeiter parameter for the new instance
     */
    public Customer(Integer cId, String benutzername, String passwort, String email, int rolle, Date zeitstempel, Boolean istmitarbeiter) {
        this.cId = cId;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.email = email;
        this.rolle = rolle;
        this.zeitstempel = zeitstempel;
        this.istmitarbeiter = istmitarbeiter;
    }

    /**
     * Get Value of cId
     * 
     * @return the value of cId
     */
    public Integer getCId() {
        return cId;
    }

    /**
     * Set Value of cId
     * 
     * @param cId the new value of cId
     */
    public void setCId(Integer cId) {
        this.cId = cId;
    }

    /**
     * Get Value of benutzername
     * 
     * @return the value of benutzername
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Set Value of benutzername
     * 
     * @param benutzername the new value of benutzername
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Get Value of passwort
     * 
     * @return the value of passwort
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Set Value of passwort
     * 
     * @param passwort the new value of passwort
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Get Value of email
     * 
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set Value of email
     * 
     * @param email the new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get Value of rolle
     * 
     * @return the value of rolle
     */
    public int getRolle() {
        return rolle;
    }

    /**
     * Set Value of rolle
     * 
     * @param rolle the new value of rolle
     */
    public void setRolle(int rolle) {
        this.rolle = rolle;
    }

    /**
     * Get Value of zeitstempel
     * 
     * @return the value of zeitstempel
     */
    public Date getZeitstempel() {
        return zeitstempel;
    }

    /**
     * Set Value of zeitstempel
     * 
     * @param zeitstempel the new value of zeitstempel
     */
    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    /**
     * Get Value of istmitarbeiter
     * 
     * @return the value of istmitarbeiter
     */
    public Boolean getIstmitarbeiter() {
        return istmitarbeiter;
    }

    /**
     * Set Value of istmitarbeiter
     * 
     * @param istmitarbeiter the new value of istmitarbeiter
     */
    public void setIstmitarbeiter(Boolean istmitarbeiter) {
        this.istmitarbeiter = istmitarbeiter;
    }

    /**
     * Get Value of ordersCollection
     * 
     * @return the value of ordersCollection
     */
    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    /**
     * Set Value of ordersCollection
     * 
     * @param ordersCollection the new value of ordersCollection
     */
    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
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
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cId != null ? cId.hashCode() : 0);
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
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
        return "de.hsbi.fotostudio.modul.Customer[ cId=" + cId + " ]";
    }
    
}
