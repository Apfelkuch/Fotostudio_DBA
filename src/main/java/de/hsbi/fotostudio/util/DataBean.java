package de.hsbi.fotostudio.util;

import de.hsbi.fotostudio.modul.BillingType;
import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.modul.Category;
import de.hsbi.fotostudio.modul.Product;
import de.hsbi.fotostudio.modul.Service;
import de.hsbi.fotostudio.modul.StorageStatus;
import de.hsbi.fotostudio.modul.User;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    Connection conn;
    
    
    private List<User> loginListDAO;
    private List<Product> product_list;
    private List<Service> service_list;
    private List<BillingType> billingType_list;
    private List<Category> product_category_list;
    private List<Category> service_category_list;
    private List<StorageStatus> storageStatus_list;

    /**
     * Creates a new instance of DataBean
     */
    public DataBean() {
    }
    
    @PostConstruct
    public void init() {
        openConnection();
        
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
    
    public void openConnection() {
        try {
            String dbUrl = "jdbc:mariadb://localhost:3306/fotostudio_dba";
            
            conn = DriverManager.getConnection(dbUrl, "fotostudio_admin", "12345");
            
            LOG.info("[DataBean] Connection to Database successfull!");
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
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
        loginListDAO.add(new User(pName, pPasword, pEmail, pBday, pRole));
    }

    /**
     * Authenticates a user by checking username and password.
     * @param user The username of the user to authenticate
     * @param password The password of the user to authenticate
     * @return The authenticated user if successful, otherwise null
     */
    public User login(String user, String password) {
        for (int i = 0; i < loginListDAO.size(); i++) {
            if (user.equals(loginListDAO.get(i).getUsername())
                    && password.equals(loginListDAO.get(i).getPassword())) {
                return loginListDAO.get(i);
            }
        }
        return null;
    }

    /**
     * Checks if a user exists.
     * @param user The username to check
     * @return True if the user exists, otherwise false
     */
    public boolean userExist(String user) {
        for (int i = 0; i < loginListDAO.size(); i++) {
            if (user.equals(loginListDAO.get(i).getUsername())) {
                return true;
            }
        }
        
        if (conn == null) {
            openConnection();
        }
        
        try {
            String sql = "SELECT COUNT(U_ID) AS ANZAHL FROM Benutzer WHERE NAME = ?";
            
            PreparedStatement preStmt = conn.prepareStatement(sql);
            
            preStmt.setString(1, user);
            
            ResultSet rs = preStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException exception) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, exception);
        }
        
        return false;
    }
    
    /**
     * Returns a List of all Users which purchased something in the last
     * 3 months. The Users are sorted in descending order of their purchases.
     * 
     * @return list of top seller users
     */
    public List<User> selectTopSeller() {
        return loginListDAO;
    }
    
    /**
     * Returns a List of all Users which have not purchased something
     * this year.
     * 
     * @return list of the shop keeper users
     */
    public List<User> selectShopKeeper() {
        return loginListDAO;
    }
    
    /**
     * Adds new product to the product list
     * 
     * @param product the new product which is added, the id will be overwritten
     * @return the added product with the new id
     */
    public Product addProduct_list(Product product) {
        product.setId(product_list.size());
        product_list.add(product);
        return product;
    }
    
    /**
     * Updates a Product in product list
     * 
     * @param id the id of the Product which should be updated
     * @param product the Product with the new data
     * @return true if the Product is found and updated, otherwise false
     */
    public boolean updateProduct_list(int id, Product product) {
        if (id >= 0 && id < product_list.size()) {
            return this.product_list.set(id, product).equals(product);
        }
        return false;
    }
    
    /**
     * Finds a product in the product list and returns the found product.
     * If no product is found null is returned.
     * 
     * @param id the product which is search for
     * @return the searches product if the search was successfull overwise null
     */
    public Product findProduct_list(int id) {
        if (id < 0 || id >= product_list.size())
            return null;
        return product_list.get(id);
    }
    
    /**
     * Adds new service to the service list
     * 
     * @param service the new service which is added, the id will be overwritten
     * @return the added service with the new id
     */
    public Service addService_list(Service service) {
        if (conn == null) {
            openConnection();
        }
        
        try {
            String sql = "INSERT INTO Service(NAME, BESCHREIBUNG, KATEGORIE, ABRECHNUNGSART, PREIS, LAGERSTATUS, DATEINAME) "
                    + "VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, service.getName());
            preStmt.setString(2, service.getDescription());
            preStmt.setInt(3, service.getCategory().getId());
            preStmt.setInt(4, service.getBillingType().getId());
            preStmt.setDouble(5, service.getPrice());
            preStmt.setInt(6, service.getStorageStatus().getId());
            preStmt.setString(7, service.getImage());
            
            preStmt.executeUpdate();
            
            int autoincremntId = -1;
            ResultSet key = preStmt.getGeneratedKeys();
            if (key.next())
                autoincremntId = key.getInt(1);
            
            service.setId(autoincremntId);
            
            return service;
        } catch (SQLException exception) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
    }
    
    /**
     * Updates a Service in service list
     * 
     * @param id the id of the Service which should be updated
     * @param service the Service with the new data
     * @return true if the service is found and updated, otherwise false
     */
    public boolean updateService_list(int id, Service service) {
        if (conn == null) {
            openConnection();
        }
            
        try {

            String sql = "UPDATE Service SET "
                    + "NAME='?',BESCHREIBUNG='?',KATEGORIE='?',ABRECHNUNGSART='?',PREIS='?',LAGERSTATUS='?',DATEINAME='?'"
                    + "WHERE S_ID = ?";
            
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, service.getName());
            preStmt.setString(2, service.getDescription());
            preStmt.setInt(3, service.getCategory().getId());
            preStmt.setInt(4, service.getBillingType().getId());
            preStmt.setDouble(5, service.getPrice());
            preStmt.setInt(6, service.getStorageStatus().getId());
            preStmt.setString(7, service.getImage());
            preStmt.setInt(8, id);
            
            preStmt.executeUpdate();
            
        } catch (SQLException exception) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, exception);
        }
        return false;
    }
    
    /**
     * Finds a service in the service list and returns the found service.
     * If no service is found null is returned.
     * 
     * @param id the service which is search for
     * @return the searches service if the search was successfull overwise null
     */
    public Service findService_list(int id) {
        if (conn == null) {
            openConnection();
        }
        
        try {
            String sql = "SELECT * FROM Service WHERE P_ID = ?";
            
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            
            ResultSet rs = preStmt.executeQuery();
            
            if (rs.next()) {
                return new Service(
                        rs.getInt("S_ID"),
                        rs.getString("NAME"),
                        rs.getString("BESCHREIBUNG"),
                        (Category) rs.getObject("CATEGORY"),
                        (BillingType) rs.getObject("ABRECHNUNGSART"),
                        rs.getFloat("PREIS"),
                        (StorageStatus) rs.getObject("LAGERSTATUS"),
                        rs.getString("DATEINAME")
                );
            }
        } catch (SQLException exception) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
    }

    /**
     * Get Value of product_list
     * 
     * @return the value of product_list
     */
    public List<Product> getProduct_list() {
        if (conn == null) {
            openConnection();
        }
        
        product_list = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM produkt";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                product_list.add(new Product(
                        rs.getInt("P_ID"),
                        rs.getString("NAME"),
                        rs.getString("BESCHREIBUNG"),
                        findProductCategory(rs.getString("KATEGORIE")),
                        findBillingType(rs.getString("ABRECHNUNGSART")),
                        rs.getFloat("PREIS"),
                        rs.getInt("MENGE"),
                        fingStorageStatus(rs.getString("LAGERSTATUS")),
                        rs.getString("DATEIPFAD")
                ));
            }
            return product_list;
        } catch (SQLException exception) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, exception);
        }
        return product_list;
    }
    
    /**
     * Get Value of service_list
     * 
     * @return the value of service_list
     */
    public List<Service> getService_list() {
        if (conn == null) {
            openConnection();
        }
        
        service_list = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM Service";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                service_list.add(new Service(
                        rs.getInt("P_ID"),
                        rs.getString("NAME"),
                        rs.getString("BESCHREIBUNG"),
                        (Category) rs.getObject("CATEGORY"),
                        (BillingType) rs.getObject("ABRECHNUNGSART"),
                        rs.getFloat("PREIS"),
                        (StorageStatus) rs.getObject("LAGERSTATUS"),
                        rs.getString("DATEINAME")
                ));
            }
            return service_list;
        } catch (SQLException exception) {
            Logger.getLogger(DataBean.class.getName()).log(Level.SEVERE, null, exception);
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
    
    public StorageStatus fingStorageStatus(String storageStatusString) {
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
