package de.hsbi.fotostudio.util;

import de.hsbi.fotostudio.modul.Adresse;
import de.hsbi.fotostudio.modul.BasketItem;
import de.hsbi.fotostudio.modul.BillingType;
import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.modul.Category;
import de.hsbi.fotostudio.modul.Item;
import de.hsbi.fotostudio.modul.Produkt;
import de.hsbi.fotostudio.modul.Service;
import de.hsbi.fotostudio.modul.ShowUser;
import de.hsbi.fotostudio.modul.StorageStatus;
import de.hsbi.fotostudio.modul.Customer;
import de.hsbi.fotostudio.modul.Orders;
import de.hsbi.fotostudio.modul.Produktdetail;
import de.hsbi.fotostudio.modul.Servicedetail;
import de.hsbi.fotostudio.modul.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janis Wiegräbe
 */
@Named(value = "dataBean")
@SessionScoped
public class DataBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(DataBean.class.getName());
        
    private List<Produkt> product_list;
    private List<Service> service_list;
    private List<BillingType> billingType_list;
    private List<Category> product_category_list;
    private List<Category> service_category_list;
    private List<StorageStatus> storageStatus_list;
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private UserTransaction ut;

    /**
     * Creates a new instance of DataBean
     */
    public DataBean() {
    }
    
    @PostConstruct
    public void init() {
        
        product_category_list = new ArrayList<>();
        product_category_list.add(new Category(0, "Alles"));
        product_category_list.add(new Category(1, "Equipment"));
        
        service_category_list = new ArrayList<>();
        service_category_list.add(new Category(0, "Alles"));
        service_category_list.add(new Category(1, "Innerhaus"));
        service_category_list.add(new Category(2, "Außerhaus"));
        
        storageStatus_list = new ArrayList<>();
        storageStatus_list.add(new StorageStatus(0, "In Stock"));
        storageStatus_list.add(new StorageStatus(1, "Low Stock"));
        storageStatus_list.add(new StorageStatus(2, "Out of Stock"));
        
        billingType_list = new ArrayList<>();
        billingType_list.add(new BillingType(0, "Pro Bild"));
        billingType_list.add(new BillingType(1, "Pro Person"));
        billingType_list.add(new BillingType(2, "Pro Objekt"));
        billingType_list.add(new BillingType(3, "Pro Familie"));
    }
    
    public void addOrder(List<BasketItem> basket) {
        try {
            ut.begin();
            
            TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCId", Customer.class);
            query.setParameter("cId", Util.getCustomerId());
            Customer customer = query.getSingleResult();
            
            Orders order = new Orders();
            order.setFkCId(customer);
            order.setZeitstempel(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            order.setLieferdatum(calendar.getTime());
            
            em.persist(order);
            
            for (BasketItem basketItem : basket) {
                Item item = basketItem.getItem();
                if (item instanceof Service) { // edit service on checkout
                    Servicedetail servicedetail = new Servicedetail();
                    servicedetail.setMenge((int) basketItem.getCount());
                    servicedetail.setFkSId((Service) item);
                    servicedetail.setFkOId(order);
                    em.persist(servicedetail);
                } else if (item instanceof Produkt) { // edit product on checkout
                    Produktdetail produktdetail = new Produktdetail();
                    produktdetail.setMenge((int) basketItem.getCount());
                    produktdetail.setFkPId((Produkt) item);
                    produktdetail.setFkOId(order);
                    em.persist(produktdetail);
                } else {
                    LOG.info("[Basket] invalid item in basket");
                }
            }

            ut.commit();
        
        } catch (HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException | IllegalStateException | SecurityException ex) {
            if (em.getTransaction().isActive()) {
                try {
                    ut.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a new user to the login data.
     * @param pName The username of the new user
     * @param pPasword The password of the new user
     * @param pEmail The email of the new user
     * @param pBday The birthday of the new user
     * @param pRole The role of the new user
     */
    public void addUser(String pName, String pPasword, String pEmail, Birthday pBday, int pRole) {
        try {
            ut.begin();

            TypedQuery<Adresse> query = em.createNamedQuery("Adresse.findByAId", Adresse.class);
            query.setParameter("aId", 5);
            Adresse adresse = query.getSingleResult();

            User user = new User();
            user.setName(pName);
            user.setVorname("Hans");
            user.setGeburtsdatum(Birthday.convertBirthdayToDate(pBday));
            user.setFkAId(adresse);
            
            em.persist(user);

            Customer customer = new Customer();
            customer.setBenutzername(pName);
            customer.setPasswort(pPasword);
            customer.setEmail(pEmail);
            customer.setRolle(0);
            customer.setFkUId(user);
            customer.setIstmitarbeiter(false);
            customer.setZeitstempel(new Date());
            
            em.persist(customer);

            ut.commit();
        
        } catch (HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException | IllegalStateException | SecurityException ex) {
            if (em.getTransaction().isActive()) {
                try {
                    ut.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Authenticates a customer by checking username and password.
     * @param user The username of the user to authenticate
     * @param password The password of the user to authenticate
     * @return The authenticated user if successful, otherwise null
     */
    public Customer login(String user, String password) {
        try {
            TypedQuery<Customer> query = em.createNamedQuery("Customer.login", Customer.class);
            query.setParameter("benutztername", user);
            query.setParameter("passwort", password);
            
            List<Customer> customers = query.getResultList();
            
            return customers.size() == 1 ? customers.get(0) : null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Checks if a user exists.
     * @param user The username to check
     * @return True if the user exists, otherwise false
     */
    public boolean userExist(String user) {
        try {
            Query query = em.createNamedQuery("Customer.exists");
            query.setParameter("benutzername", user);
            
            long userCount = (long) query.getSingleResult();
            
            return userCount > 0;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return true;
    }
    
    /**
     * Returns a List of all Users which purchased something in the last
     * 3 months. The Users are sorted in descending order of their purchases.
     * 
     * @return list of top seller users
     */
    public List<ShowUser> selectTopSellerCustomer() {
        try {
            List<ShowUser> topSeller = new ArrayList<>();
            
            Query query = em.createQuery("SELECT c,COUNT(o.oId) as Anzahl FROM Orders o LEFT JOIN FETCH o.fkCId c WHERE o.zeitstempel > :timeBorder GROUP BY c.cId HAVING Anzahl > 1 ORDER BY c.benutzername DESC");
            
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -2);
            query.setParameter("timeBorder", calendar.getTime());
            
            List<Object[]> entries = query.getResultList();
            for (Object[] entry : entries) {
                Customer customer = (Customer) entry[0];
                topSeller.add(new ShowUser(
                        customer.getBenutzername(),
                        customer.getEmail(),
                        ((Long) entry[1]).intValue()
                ));
            }
            return topSeller;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return new ArrayList<>();
    }
    
    /**
     * Returns a List of all Items which were purchased in the last
     * 3 months. The Items are sorted in descending order of their last purchase.
     * 
     * @return list of top seller items
     */
    public List<ShowUser> selectTopSellerItem() {
        // todo
        return new ArrayList<>();
    }
    
    /**
     * Returns a List of all Users which have not purchased something
     * this year.
     * 
     * @return list of the shop keeper users
     */
    public List<ShowUser> selectShopKeeperCustomer() {
        try {
            List<ShowUser> shopKeeper = new ArrayList<>();
            
            Query query = em.createQuery("SELECT c,COUNT(o.oId) as Anzahl FROM Orders o LEFT JOIN FETCH o.fkCId c WHERE o.zeitstempel > :timeBorder GROUP BY c.cId HAVING Anzahl = 0 ORDER BY c.benutzername ASC");
            
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            query.setParameter("timeBorder", calendar.getTime());
            
            List<Object[]> entries = query.getResultList();
            for (Object[] entry : entries) {
                Customer customer = (Customer) entry[0];
                shopKeeper.add(new ShowUser(
                        customer.getBenutzername(),
                        customer.getEmail(),
                        ((Long) entry[1]).intValue()
                ));
            }
            
            return shopKeeper;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return new ArrayList<>();
    }
    
    /**
     * Returns a List of all Users which have not purchased something
     * this year.
     * 
     * @return list of the shop keeper users
     */
    public List<ShowUser> selectShopKeeperItem() {
        // todo
        return new ArrayList<>();
    }
    
    /**
     * Adds new product to the product list
     * 
     * @param product the new product which is added, the id will be overwritten
     * @return the added product with the new id
     */
    public Produkt addProduct_list(Produkt product) {
        try {
            ut.begin();
            product.setZeitstempel(new Date());
            product.setDateipfad("image_not_found.jpg");
            em.persist(product);
            ut.commit();
        } catch (HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException | IllegalStateException | SecurityException ex) {
            if (em.getTransaction().isActive()) {
                try {
                    ut.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    
    /**
     * Updates a Product in product list
     * 
     * @param id the id of the Product which should be updated
     * @param product the Product with the new data
     * @return true if the Product is found and updated, otherwise false
     */
    public boolean updateProduct_list(int id, Produkt product) {
        try {
            ut.begin();
            product.setZeitstempel(new Date());
            em.merge(product);
            ut.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex);
            try {
                ut.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }
    
    /**
     * Finds a product in the product list and returns the found product.
     * If no product is found null is returned.
     * 
     * @param id the product which is search for
     * @return the searches product if the search was successfull overwise null
     */
    public Produkt findProduct_list(int id) {
        TypedQuery<Produkt> query = em.createNamedQuery("Produkt.findByPId", Produkt.class);
        query.setParameter("pId", id);
        
        return query.getSingleResult();
    }
    
    /**
     * Adds new service to the service list
     * 
     * @param service the new service which is added, the id will be overwritten
     * @return the added service with the new id
     */
    public Service addService_list(Service service) {
        try {
            ut.begin();
            service.setZeitstempel(new Date());
            service.setDateipfad("image_not_found.jpg");
            em.persist(service);
            ut.commit();
        } catch (HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException | IllegalStateException | SecurityException ex) {
            if (em.getTransaction().isActive()) {
                try {
                    ut.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }
    
    /**
     * Updates a Service in service list
     * 
     * @param id the id of the Service which should be updated
     * @param service the Service with the new data
     * @return true if the service is found and updated, otherwise false
     */
    public boolean updateService_list(int id, Service service) {
        try {
            ut.begin();
            service.setZeitstempel(new Date());
            em.merge(service);
            ut.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex);
            try {
                ut.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }
    
    /**
     * Finds a service in the service list and returns the found service.
     * If no service is found null is returned.
     * 
     * @param id the service which is search for
     * @return the searches service if the search was successfull overwise null
     */
    public Service findService_list(int id) {
        TypedQuery<Service> query = em.createNamedQuery("Service.findBySId", Service.class);
        query.setParameter("sId", id);
        
        return query.getSingleResult();
    }

    /**
     * Get Value of product_list
     * 
     * @return the value of product_list
     */
    public List<Produkt> getProduct_list() {
        TypedQuery<Produkt> query = em.createNamedQuery("Produkt.findAll", Produkt.class);
        product_list = query.getResultList();
        
        for (Produkt p : product_list) {
            p.setCategory(findProductCategory(p.getKategorieString()));
            p.setBillingType(findBillingType(p.getAbrechnungsartString()));
            p.setStorageStatus(findStorageStatus(p.getLagerstatusString()));
        }
        
        return product_list;
    }
    
    /**
     * Get Value of service_list
     * 
     * @return the value of service_list
     */
    public List<Service> getService_list() {
        TypedQuery<Service> query = em.createNamedQuery("Service.findAll", Service.class);
        service_list = query.getResultList();
        
        for (Service s : service_list) {
            s.setCategory(findServiceCategory(s.getKategorieString()));
            s.setBillingType(findBillingType(s.getAbrechnungsartString()));
            s.setStorageStatus(findStorageStatus(s.getLagerstatusString()));
        }
        
        return service_list;
    }
    
    public Category findProductCategory(String categoryString) {
        Category category = product_category_list.get(0);
        for (Category c : product_category_list) {
            if (c.getName().equals(categoryString)) {
                category = c;
                break;
            }
        }
        return category;
    }
    
    public Category findServiceCategory(String categoryString) {
        Category category = service_category_list.get(0);
        for (Category c : service_category_list) {
            if (c.getName().equals(categoryString)) {
                category = c;
                break;
            }
        }
        return category;
    }
    
    public BillingType findBillingType(String billingTypeString) {
        BillingType billingType = billingType_list.get(0);
        for (BillingType b : billingType_list) {
            if (b.getName().equals(billingTypeString)) {
                billingType = b;
                break;
            }
        }
        return billingType;
    }
    
    public StorageStatus findStorageStatus(String storageStatusString) {
        StorageStatus storageStatus = storageStatus_list.get(0);
        for (StorageStatus s : storageStatus_list) {
            if (s.getName().equals(storageStatusString)) {
                storageStatus = s;
                break;
            }
        }
        return storageStatus;
    }

    /**
     * Get Value of product_category_list
     * 
     * @return the value of product_category_list
     */
    public List<Category> getProduct_category_list() {
        return product_category_list;
    }

    /**
     * Get Value of service_category_list
     * 
     * @return the value of service_category_list
     */

    public List<Category> getService_category_list() {
        return service_category_list;
    }

    /**
     * Get Value of billingType_list
     * 
     * @return the value of billingType_list
     */
    public List<BillingType> getBillingType_list() {
        return billingType_list;
    }

    /**
     * Get Value of storageStatus_list
     * 
     * @return the value of storageStatus_list
     */
    public List<StorageStatus> getStorageStatus_list() {
        return storageStatus_list;
    }
    
}
