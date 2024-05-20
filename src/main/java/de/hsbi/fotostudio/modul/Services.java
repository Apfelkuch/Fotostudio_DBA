package de.hsbi.fotostudio.modul;

import de.hsbi.fotostudio.util.ProductData;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The class Services is the connecting class between the Backing-Beans
 * and the data of the shop.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "services")
@ApplicationScoped
public class Services {

    private static final Logger LOG = Logger.getLogger(Services.class.getName());
    
    private List<Service> currentServices;
    
    private Service currentService;
    
    @Inject
    private ProductData productData;
    
    /**
     * Creates a new instance of Service
     */
    public Services() {
    }
    
    /**
     * Initalization of the Products is called directly after the Konstruktor
     * Initalizes the variables and sets a default Category
     */
    @PostConstruct
    public void init() {
        currentServices = new ArrayList<>();
        selectServiceCategory(0);
        currentService = new Service();
    }
    
    /**
     * This Methode updates the list currentServices to only contain 
     * Serices, which have the same category as the given categorieId
     * 
     * @param categorieId new id of the selected category
     */
    public void selectServiceCategory(int categorieId) {
        Category category = productData.getCategory_list().get(categorieId);
        LOG.info("[Services] category of the currentServices is updated, new Category is: " + category.getName());
        List<Service> immutableCopy = List.copyOf(productData.getService_list());
        currentServices = null;
        if (categorieId == 0) {
            currentServices = immutableCopy;
        } else {
            currentServices = immutableCopy.stream()
                    .filter(service -> service.inCategory(category))
                    .collect(Collectors.toList());
        }
        if (currentServices.isEmpty()) {
            LOG.info("[Services] Keine Kategory gefunden");
            currentServices = new ArrayList<>();
        }
    }
        
    /**
     * Updates a Service in ProductData and reselects the category to update
     * the changes in the ServiceView
     * 
     * @param id the id of the Service which should be updated
     * @param service the Service with the new data
     * @return true if the Service is found and updated, otherwise false
     */
    public boolean updateService(int id, Service service) {
        if (id < 0 || id >= productData.getService_list().size()){
            return false;
        }
        
        // update Service daten in list (database)
        boolean returnValue = productData.updateService_list(id, service);
        
        // set Category to reload the product list
        selectServiceCategory(currentService.getCategory().getId());
        
        return returnValue;
    }
    
    /**
     * This Methode finds the Category to a given id
     * 
     * @param id the id of the Category this Methode searchs for 
     * @return the category to the given id
     */
    public Category findCategoryWithId(int id) {
        LOG.info("[Services] findCategoryWithId => id: " + id);
        for (Category k : productData.getCategory_list()) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
    }
        
    /**
     * This Methode finds the StorageStatus to a given id
     * 
     * @param id the id of the StorageStatus this Methode searchs for 
     * @return the StorageStatus to the given id
     */
    public StorageStatus findStorageStatusWithId(int id) {
        LOG.info("[Services] billingType => id: " + id);
        for (StorageStatus l : productData.getStorageStatus_list()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
    
    // GETTER && SETTER

    /**
     * Get Value of id
     * 
     * @return the value of id
     */
    public List<Service> getCurrentServices() {
//        LOG.info("[Services] aktuell erstes Product: " + aktuelle_produkt_liste.get(0).getName());
        return currentServices;
    }

    /**
     * Get Value of currentService
     * 
     * @return the value of currentService
     */
    public Service getCurrentService() {
        return currentService;
    }

    /**
     * Set Value of currentService
     * 
     * @param currentService the new value of currentService
     */
    public void setCurrentService(Service currentService) {
        LOG.info("[Services] current Product: " + currentService.getName());
        this.currentService = currentService;
    }
}
