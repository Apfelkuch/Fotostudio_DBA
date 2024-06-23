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
 * This class is model for a Produkt
 *
 * @version 1.1
 * @author Janis Wiegräbe
 */
@Entity
@Table(name = "produkt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p"),
    @NamedQuery(name = "Produkt.findByPId", query = "SELECT p FROM Produkt p WHERE p.pId = :pId"),
    @NamedQuery(name = "Produkt.findByName", query = "SELECT p FROM Produkt p WHERE p.name = :name"),
    @NamedQuery(name = "Produkt.findByBeschreibung", query = "SELECT p FROM Produkt p WHERE p.beschreibung = :beschreibung"),
    @NamedQuery(name = "Produkt.findByKategorie", query = "SELECT p FROM Produkt p WHERE p.kategorie = :kategorie"),
    @NamedQuery(name = "Produkt.findByAbrechnungsart", query = "SELECT p FROM Produkt p WHERE p.abrechnungsart = :abrechnungsart"),
    @NamedQuery(name = "Produkt.findByPreis", query = "SELECT p FROM Produkt p WHERE p.preis = :preis"),
    @NamedQuery(name = "Produkt.findByMenge", query = "SELECT p FROM Produkt p WHERE p.menge = :menge"),
    @NamedQuery(name = "Produkt.findByLagerstatus", query = "SELECT p FROM Produkt p WHERE p.lagerstatus = :lagerstatus"),
    @NamedQuery(name = "Produkt.findByZeitstempel", query = "SELECT p FROM Produkt p WHERE p.zeitstempel = :zeitstempel"),
    @NamedQuery(name = "Produkt.findByDateipfad", query = "SELECT p FROM Produkt p WHERE p.dateipfad = :dateipfad"),
    @NamedQuery(name = "Produkt.update", query = "UPDATE Produkt p SET p.name = :name, p.beschreibung = :beschreibung, p.kategorie = :kategorie, p.abrechnungsart = :abrechnungsart, p.preis = :preis, p.menge = :menge, p.lagerstatus = :lagerstatus, p.zeitstempel = :zeitstempel WHERE p.pId = :pId")
})
public class Produkt extends Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "P_ID")
    private Integer pId;
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
    private BigDecimal preis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENGE")
    private long menge;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPId")
    private Collection<Produktdetail> produktdetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPId")
    private Collection<Lieferumfang> lieferumfangCollection;

    
    /**
     * Creates instance of Service
     */
    public Produkt() {
        this.pId = -1;
    }

    
    /**
     * Creates instance of Service
     * 
     * @param pId the pId parameter for the new instance
     */
    public Produkt(Integer pId) {
        this.pId = pId;
    }

    
    /**
     * Creates instance of Service
     * 
     * @param pId the pId parameter for the new instance
     * @param name the name parameter for the new instance
     * @param beschreibung the beschreibung parameter for the new instance
     * @param kategorie the category kategorie for the new instance
     * @param abrechnungsart the abrechnungsart parameter for the new instance
     * @param preis the preis parameter for the new instance
     * @param menge the menge parameter fot the new instance
     * @param lagerstatus the lagerstatus parameter for the new instance
     * @param zeitstempel the zeitstempel parameter for the new instance
     * @param dateipfad the dateipfad path parameter for the new instance
     */
    public Produkt(Integer pId, String name, String beschreibung, String kategorie, String abrechnungsart, BigDecimal preis, long menge, String lagerstatus, Date zeitstempel, String dateipfad) {
        this.pId = pId;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.abrechnungsart = abrechnungsart;
        this.preis = preis;
        this.menge = menge;
        this.lagerstatus = lagerstatus;
        this.zeitstempel = zeitstempel;
        this.dateipfad = dateipfad;
    }

    /**
     * Get Value of pId
     * 
     * @return the value of pId
     */
    public Integer getPId() {
        return pId;
    }

    /**
     * Set Value of pId
     * 
     * @param pId the new value of pId
     */
    public void setPId(Integer pId) {
        this.pId = pId;
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

    /**
     * Get Value of beschreibung
     * 
     * @return the value of beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Set Value of beschreibung
     * 
     * @param beschreibung the new value of beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Get Value of category
     * 
     * @return the value of category
     */
    public Category getKategorie() {
        return category;
    }

    /**
     * Get Value of category
     * 
     * @return the value of category
     */
    public Category getKategorieff() {
        return category;
    }
    
    /**
     * Get Value of kategorie
     * 
     * @return the value of kategorie
     */
    public String getKategorieString() {
        return kategorie;
    }

    /**
     * Set Value of category and kategorie
     * 
     * @param category the new value of category
     */
    public void setKategorie(Category category) {
        this.kategorie = category.getName();
        this.category = category;
    }

    /**
     * Set Value of category and kategorie
     * 
     * @param category the new value of category
     */
    public void setKategorieff(Category category) {
        this.kategorie = category.getName();
        this.category = category;
    }

    /**
     * Get Value of billingType
     * 
     * @return the value of billingType
     */
    public BillingType getAbrechnungsart() {
        return billingType;
    }

    /**
     * Get Value of abrechnungsart
     * 
     * @return the value of abrechnungsart
     */
    public BillingType getAbrechnungsartff() {
        return billingType;
    }
    
    /**
     * Get Value of abrechnungsart
     * 
     * @return the value of abrechnungsart
     */
    public String getAbrechnungsartString() {
        return abrechnungsart;
    }

    /**
     * Set Value of billingType and abrechnungsart
     * 
     * @param billingType the new value of billingType
     */
    public void setAbrechnungsart(BillingType billingType) {
        this.abrechnungsart = billingType.getName();
        this.billingType = billingType;
    }

    /**
     * Set Value of billingType and abrechnungsart
     * 
     * @param billingType the new value of billingType
     */
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
    
    /**
     * Get Value of menge
     * 
     * @return the value of menge
     */
    @Override
    public long getMenge() {
        return menge;
    }

    /**
     * Set Value of menge
     * 
     * @param menge the new value of menge
     */
    public void setMenge(long menge) {
        this.menge = menge;
    }
    
    /**
     * Get Value of storageStatus
     * 
     * @return the value of storageStatus
     */
    public StorageStatus getLagerstatus() {
        return storageStatus;
    }
    
    /**
     * Get Value of storageStatus
     * 
     * @return the value of storageStatus
     */
    public StorageStatus getLagerstatusff() {
        return storageStatus;
    }
    
    /**
     * Get Value of lagerstatus
     * 
     * @return the value of lagerstatus
     */
    public String getLagerstatusString() {
        return lagerstatus;
    }


    /**
     * Set Value of storageStatus and lagerstatus
     * 
     * @param storageStatus the new value of storageStatus
     */
    public void setLagerstatus(StorageStatus storageStatus) {
        this.lagerstatus = storageStatus.getName();
        this.storageStatus = storageStatus;
    }


    /**
     * Set Value of storageStatus and lagerstatus
     * 
     * @param storageStatus the new value of storageStatus
     */
    public void setLagerstatusff(StorageStatus storageStatus) {
        this.lagerstatus = storageStatus.getName();
        this.storageStatus = storageStatus;
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
     * Get Value of dateipfad
     * 
     * @return the value of dateipfad
     */
    public String getDateipfad() {
        return dateipfad;
    }

    /**
     * Set Value of dateipfad
     * 
     * @param dateipfad the new value of dateipfad
     */
    public void setDateipfad(String dateipfad) {
        this.dateipfad = dateipfad;
    }

    /**
     * Get Value of produktdetailCollection
     * 
     * @return the value of produktdetailCollection
     */
    @XmlTransient
    public Collection<Produktdetail> getProduktdetailCollection() {
        return produktdetailCollection;
    }

    /**
     * Set Value of produktdetailCollection
     * 
     * @param produktdetailCollection the new value of produktdetailCollection
     */
    public void setProduktdetailCollection(Collection<Produktdetail> produktdetailCollection) {
        this.produktdetailCollection = produktdetailCollection;
    }

    /**
     * Get Value of lieferumfangCollection
     * 
     * @return the value of lieferumfangCollection
     */
    @XmlTransient
    public Collection<Lieferumfang> getLieferumfangCollection() {
        return lieferumfangCollection;
    }

    /**
     * Set Value of lieferumfangCollection
     * 
     * @param lieferumfangCollection the new value of lieferumfangCollection
     */
    public void setLieferumfangCollection(Collection<Lieferumfang> lieferumfangCollection) {
        this.lieferumfangCollection = lieferumfangCollection;
    }

    /**
     * Generates hash code of the class using the id parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pId != null ? pId.hashCode() : 0);
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
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        if ((this.pId == null && other.pId != null) || (this.pId != null && !this.pId.equals(other.pId))) {
            return false;
        }
        return true;
    }

    /**
     * Converts the Obeject date into a String, for easier debugging
     * 
     * @return the String which represants the Object data
     */
    @Override
    public String toString() {
        return "de.hsbi.fotostudio.modul.Produkt[ "+ pId + ", " + name + ", " + beschreibung 
                + ", " + category.getName() + ", " + billingType.getName()
                + ", " + preis + ", " + storageStatus.getName() + ", " + menge
                + ", " + dateipfad + "]";
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
    
}
