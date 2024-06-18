package de.hsbi.fotostudio.modul;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * This class is model for a Service
 *
 * @version 1.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findBySId", query = "SELECT s FROM Service s WHERE s.sId = :sId"),
    @NamedQuery(name = "Service.findByName", query = "SELECT s FROM Service s WHERE s.name = :name"),
    @NamedQuery(name = "Service.findByBeschreibung", query = "SELECT s FROM Service s WHERE s.beschreibung = :beschreibung"),
    @NamedQuery(name = "Service.findByKategorie", query = "SELECT s FROM Service s WHERE s.kategorie = :kategorie"),
    @NamedQuery(name = "Service.findByAbrechnungsart", query = "SELECT s FROM Service s WHERE s.abrechnungsart = :abrechnungsart"),
    @NamedQuery(name = "Service.findByPreis", query = "SELECT s FROM Service s WHERE s.preis = :preis"),
    @NamedQuery(name = "Service.findByLagerstatus", query = "SELECT s FROM Service s WHERE s.lagerstatus = :lagerstatus"),
    @NamedQuery(name = "Service.findByZeitstempel", query = "SELECT s FROM Service s WHERE s.zeitstempel = :zeitstempel"),
    @NamedQuery(name = "Service.findByDateipfad", query = "SELECT s FROM Service s WHERE s.dateipfad = :dateipfad"),
    @NamedQuery(name = "Service.update", query = "UPDATE Service s SET s.name = :name, s.beschreibung = :beschreibung, s.kategorie = :kategorie, s.abrechnungsart = :abrechnungsart, s.preis = :preis, s.lagerstatus = :lagerstatus, s.zeitstempel = :zeitstempel WHERE s.sId = :sId")
})
public class Service extends Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "S_ID")
    private Integer sId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BESCHREIBUNG")
    private String beschreibung;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "KATEGORIE")
    private String kategorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ABRECHNUNGSART")
    private String abrechnungsart;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PREIS")
    protected BigDecimal preis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "LAGERSTATUS")
    private String lagerstatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZEITSTEMPEL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zeitstempel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DATEIPFAD")
    private String dateipfad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSId")
    private Collection<Servicedetail> servicedetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSId")
    private Collection<Servicepersonal> servicepersonalCollection;

    public Service() {
        this(-1);
    }

    public Service(Integer sId) {
        this.sId = sId;
    }

    public Service(Integer sId, String name, String beschreibung, String kategorie, String abrechnungsart, BigDecimal preis, String lagerstatus, Date zeitstempel, String dateipfad)
    {
        this.sId = sId;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.abrechnungsart = abrechnungsart;
        this.preis = preis;
        this.lagerstatus = lagerstatus;
        this.zeitstempel = zeitstempel;
        this.dateipfad = dateipfad;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }
    
    /**
     * Get Value of name
     * 
     * @return the value of name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set Value of name
     * 
     * @param name the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Category getKategorie() {
        return category;
    }

    public Category getKategorieff() {
        return category;
    }
    
    public String getKategorieString() {
        return kategorie;
    }

    public void setKategorie(Category category) {
        this.kategorie = category.getName();
        this.category = category;
    }

    public void setKategorieff(Category category) {
        this.kategorie = category.getName();
        this.category = category;
    }

    public BillingType getAbrechnungsart() {
        return billingType;
    }

    public BillingType getAbrechnungsartff() {
        return billingType;
    }
    
    public String getAbrechnungsartString() {
        return abrechnungsart;
    }

    public void setAbrechnungsart(BillingType billingType) {
        this.abrechnungsart = billingType.getName();
        this.billingType = billingType;
    }

    public void setAbrechnungsartff(BillingType billingType) {
        this.abrechnungsart = billingType.getName();
        this.billingType = billingType;
    }
    
    /**
     * Get Value of preis
     * 
     * @return the value of preis
     */
    @Override
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * Set Value of preis
     * 
     * @param preis the new value of preis
     */
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public StorageStatus getLagerstatus() {
        return storageStatus;
    }
    
    public StorageStatus getLagerstatusff() {
        return storageStatus;
    }
    
    public String getLagerstatusString() {
        return lagerstatus;
    }

    public void setLagerstatus(StorageStatus storageStatus) {
        this.lagerstatus = storageStatus.getName();
        this.storageStatus = storageStatus;
    }

    public void setLagerstatusff(StorageStatus storageStatus) {
        this.lagerstatus = storageStatus.getName();
        this.storageStatus = storageStatus;
    }

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public String getDateipfad() {
        return dateipfad;
    }

    public void setDateipfad(String dateipfad) {
        this.dateipfad = dateipfad;
    }

    @XmlTransient
    public Collection<Servicedetail> getServicedetailCollection() {
        return servicedetailCollection;
    }

    public void setServicedetailCollection(Collection<Servicedetail> servicedetailCollection) {
        this.servicedetailCollection = servicedetailCollection;
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
        hash += (sId != null ? sId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.sId == null && other.sId != null) || (this.sId != null && !this.sId.equals(other.sId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Service[ sId=" + sId + " ]";
    }
    
    /**
     * Returns a identification String for this class
     * 
     * @return the identifikation String
     */
    @Override
    public String getType() {
        return "Service";
    }
    
    /**
     * Methode to check if a this product is in a given category
     * 
     * @param category the given category, which is compared to the category of this object
     * @return true if the given category is equal to the category of this object, otherwise false
     */
    public boolean inCategory(Category category) {
        return this.category.equals(category);
    }
    
    @Override
    public long getMenge() {
        return -1;
    }
    
}
