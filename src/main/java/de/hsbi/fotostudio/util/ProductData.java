package de.hsbi.fotostudio.util;

import de.hsbi.fotostudio.modul.*;
import java.util.List;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class ProductData is used to hold the data for the Shop for the time
 * beeing. The content of this class will later be stored in a database.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
public class ProductData implements Serializable{
    
    private List<Product> product_list;
    private List<Service> service_list;
    private List<BillingType> billingType_list;
    private List<Category> product_category_list;
    private List<Category> service_category_list;
    private List<StorageStatus> storageStatus_list;
    
    /**
     * Creates new instance of ProductData
     */
    public ProductData() {
    }
    
    /**
     * Initalization of the ProductData, is called directly after the
     * Konstruktor and filles the Data which is used as dummy data in
     * this project, should later be replaced be data from a database
     * Currently data is created for:
     * product_category_list, service_category_list, storageStatus_list,
     * billingType_list, product_list, service_list
     */
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
        
        product_list = new ArrayList<>();
        product_list.add(new Product(0, "Canon EOS 2000D", "Die Canon EOS 2000D Gehäuse ist eine vielseitige und benutzerfreundliche Spiegelreflexkamera, die sich perfekt für den angehenden Fotografen eignet.", product_category_list.get(1), billingType_list.get(2), 500.0f, 1, storageStatus_list.get(0)));
        product_list.add(new Product(1, "Objektive 18-55mm DC", "Canon EF-S 18–55 mm DC III ƒ/3,5-5,6:\n - Brennweite 29-88 mm\n - Kreisförmige Blende für gute Hintergrundunschärfe", product_category_list.get(1), billingType_list.get(2), 150.0f, 3, storageStatus_list.get(0)));
        product_list.add(new Product(2, "Objektive 75-300mm III", "Canon EF 75–300 mm ƒ/4-5,6 III:\n - Telezoomobjektiv\n - Minimale Blende: 32–45", product_category_list.get(1), billingType_list.get(2), 150.0f, 2, storageStatus_list.get(0)));
        product_list.add(new Product(3, "Canon EOS 250D", "Canon EOS 250D:\n - ISO 100-25.600 (erweiterbar auf 51.200)\n - Objektivanschluss: EF / EF-S", product_category_list.get(1), billingType_list.get(2), 450.0f, 0, storageStatus_list.get(2)));
     
        service_list = new ArrayList<>();
        service_list.add(new Service(0, "Analogbilder Drucken", "Drucken von einem eigenen Bildes als Analogbild", service_category_list.get(1), billingType_list.get(0), 8f, storageStatus_list.get(0)));
        service_list.add(new Service(1, "Kalender Drucken", "Drucken von einem eigenen Kalender", service_category_list.get(1), billingType_list.get(2), 12.3f, storageStatus_list.get(0)));
        service_list.add(new Service(2, "Bilder Drucken", "Drucken von einem einem Bild", service_category_list.get(1), billingType_list.get(0), 2.5f, storageStatus_list.get(0)));
        service_list.add(new Service(3, "Poster Drucken", "Drucken von einem einem Poster", service_category_list.get(1), billingType_list.get(0), 5.5f, storageStatus_list.get(0)));
        service_list.add(new Service(4, "Passbilder", "Erstellen von 4 Passbildern", service_category_list.get(1), billingType_list.get(1), 6f, storageStatus_list.get(0)));
        service_list.add(new Service(5, "Familienbilder", "Familienbilder im eigenen Fotostudio erstellen", service_category_list.get(1), billingType_list.get(3), 32.5f, storageStatus_list.get(1)));
        service_list.add(new Service(6, "Hochzeitsbilder", "Hochzeitbilder im eigenen Fotostudio erstellen", service_category_list.get(1), billingType_list.get(3), 50f, storageStatus_list.get(0)));
        service_list.add(new Service(7, "Hochzeitsbilder", "Hochzeitbilder erstellen. Der Ort ist frei wählbar", service_category_list.get(2), billingType_list.get(3), 70, storageStatus_list.get(1)));
        service_list.add(new Service(8, "Auftragsbilder Ort", "Professionelle Bilder von einem Ort", service_category_list.get(2), billingType_list.get(2), 60f, storageStatus_list.get(0)));
        service_list.add(new Service(9, "Auftragsbilder Person", "Professionelle Bilder von einer Person", service_category_list.get(1), billingType_list.get(1), 60f, storageStatus_list.get(2)));
        
    }
    
    /**
     * Adds new category to the product categories
     * 
     * @param category the new category which is added, the id will be overwritten
     * @return the added category with the new id
     */
    public Category addProduct_Category_list(Category category) {
        category.setId(product_category_list.size());
        product_category_list.add(category);
        return category;
    }
    
    /**
     * Updates a Category in product_category_list
     * 
     * @param id the id of the Category which should be updated
     * @param category the Category with the new data
     * @return true if the Category is found an updated, otherwise false
     */
    public boolean updateProduct_Category_list(int id, Category category) {
        if (id >= 0 && id < product_category_list.size()) {
            return this.product_category_list.set(id, category).equals(category);
        }
        return false;
    }
    
    /**
     * Finds a category in the product categories and returns the found category.
     * If no category is found null is returned.
     * 
     * @param id the category which is search for
     * @return the searches category if the search was successfull overwise null
     */
    public Category findProduct_Category_list(int id) {
        if (id < 0 || id >= product_category_list.size())
            return null;
        return product_category_list.get(id);
    }
    
    /**
     * Adds new category to the service categories
     * 
     * @param category the new category which is added, the id will be overwritten
     * @return the added category with the new id
     */
    public Category addService_Category_list(Category category) {
        category.setId(service_category_list.size());
        service_category_list.add(category);
        return category;
    }
    
    /**
     * Updates a Category in service_category_list
     * 
     * @param id the id of the Category which should be updated
     * @param category the Category with the new data
     * @return true if the Category is found an updated, otherwise false
     */
    public boolean updateService_Category_list(int id, Category category) {
        if (id >= 0 && id < service_category_list.size()) {
            return this.service_category_list.set(id, category).equals(category);
        }
        return false;
    }
    
    /**
     * Finds a category in the service categories and returns
     * the found category.
     * If no category is found null is returned.
     * 
     * @param id the category which is search for
     * @return the searches category if the search was successfull
     * overwise null
     */
    public Category findService_Category_list(int id) {
        if (id < 0 || id >= service_category_list.size())
            return null;
        return service_category_list.get(id);
    }
    
    /**
     * Adds new storage status to the storage statsus list
     * 
     * @param storageStatus the new storage status which is added, the id will be overwritten
     * @return the added storage status with the new id
     */
    public StorageStatus addStorageStatus_list(StorageStatus storageStatus) {
        storageStatus.setId(storageStatus_list.size());
        storageStatus_list.add(storageStatus);
        return storageStatus;
    }
    
    /**
     * Update a StorageStatus in storageStatus_list
     * 
     * @param id the id of the storageStatus which should be updated
     * @param storageStatus the StorageStatus with the new data
     * @return true if the StorageStatus is found and updated, otherwise false
     */
    public boolean updateStorageStatus_list(int id, StorageStatus storageStatus) {
        if (id >= 0 && id < this.storageStatus_list.size()) {
            return this.storageStatus_list.set(id, storageStatus).equals(storageStatus);
        }
        return false;
    }
    
    /**
     * Finds a storage status in the storage status list and returns
     * the found storage status.
     * If no category is found null is returned.
     * 
     * @param id the storage status which is search for
     * @return the searches storage status if the search was successfull overwise null
     */
    public StorageStatus findStorageStatus_list(int id) {
        if (id < 0 || id >= storageStatus_list.size())
            return null;
        return storageStatus_list.get(id);
    }
    
    /**
     * Adds new billing type to the billing type list
     * 
     * @param billingType the new billing type which is added, the id will be overwritten
     * @return the added billing type with the new id
     */
    public BillingType addBillingType_list(BillingType billingType) {
        billingType.setId(billingType_list.size());
        billingType_list.add(billingType);
        return billingType;
    }
     
    /**
     * Update a BillingType in billingType_list
     * 
     * @param id the id of the BillingType which should be updated
     * @param billingType the BillingType with the new data
     * @return true if the BillingType is found and updated, otherwise false
     */   
    public boolean updateBillingType_list(int id, BillingType billingType) {
        if (id >= 0 && id < billingType_list.size()) {
            return this.billingType_list.set(id, billingType).equals(billingType);
        }
        return false;
    }
    
    /**
     * Finds a billing type in the billing type list
     * and returns the found billing type.
     * If no billing type is found null is returned.
     * 
     * @param id the billing type which is search for
     * @return the searches billing type if the search was successfull overwise null
     */
    public BillingType findBillingType_list(int id) {
        if (id < 0 || id >= billingType_list.size())
            return null;
        return billingType_list.get(id);
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
        service.setId(service_list.size());
        service_list.add(service);
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
        if (id >= 0 && id < service_list.size()) {
            return this.service_list.set(id, service).equals(service);
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
        if (id < 0 || id >= service_list.size())
            return null;
        return service_list.get(id);
    }
    

    // GETTER && SETTER

    /**
     * Get Value of product_list
     * 
     * @return the value of product_list
     */
    public List<Product> getProduct_list() {
        return product_list;
    }
    
    /**
     * Get Value of service_list
     * 
     * @return the value of service_list
     */
    public List<Service> getService_list() {    
        return service_list;
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
