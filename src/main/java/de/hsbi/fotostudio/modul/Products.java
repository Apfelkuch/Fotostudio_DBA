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
 * The class Product is the connecting class between the Backing-Beans and
 the data of the shop.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "products")
@ApplicationScoped
public class Products {

    private static final Logger LOG = Logger.getLogger(Products.class.getName());
    
    private List<Product> currentProducts;
    
    private Product currentProduct;
    
    @Inject
    private ProductData productData;
    
    /**
     * Creates a new instance of Produkte
     */
    public Products() {
    }
    
    /**
     * Initalization of the Products is called directly after the Konstruktor
     * Initalizes the variables and sets a default Category
     */
    @PostConstruct
    public void init() {
        currentProducts = new ArrayList<>();
        selectCategory(0);
        currentProduct = new Product();
    }
    
    /**
     * This Methode updates the list currentProducts to only contain 
     * Products, which have the same category as the given categorieId
     * 
     * @param categorieId new id of the selected category
     */
    public void selectCategory(int categorieId) {
        Category kategorie = productData.getCategory_list().get(categorieId);
        LOG.info("[Products] category of the currentProducts is updated, new Category is: " + kategorie.getName());
        List<Product> immutableCopy = List.copyOf(productData.getProduct_list());
        currentProducts = null;
        switch (categorieId) {
            case 0:
                currentProducts = immutableCopy;
                break;
            case 1:
                currentProducts = immutableCopy.stream()
                        .filter(produkt -> produkt.inCategory(kategorie))
                        .collect(Collectors.toList());
                break;
            case 2:
                currentProducts = immutableCopy.stream()
                        .filter(produkt -> produkt.inCategory(kategorie))
                        .collect(Collectors.toList());
                break;
            case 3:
                currentProducts = immutableCopy.stream()
                        .filter(produkt -> produkt.inCategory(kategorie))
                        .collect(Collectors.toList());
                break;
            default:
                LOG.info("[Produkte] Keine Kategory gefunden");
                currentProducts = new ArrayList<>();
        }
//        if (!aktuelle_produkt_liste.isEmpty())
//            LOG.info("[Products] aktuell erstes Product nach selektion: " + aktuelle_produkt_liste.get(0).getName());
    }
        
    /**
     * Updates a Product in produkte and reselects the category to update
     * the changes in the ProductView
     * 
     * @param id the id of the Product which should be updated
     * @param produkt the Product with the new data
     * @return true if the Product is found and updated, otherwise false
     */
    public boolean updateProdukt(int id, Product produkt) {
        if (id < 0 || id >= productData.getProduct_list().size()){
            return false;
        }
        
        // update Product daten in list (database)
        boolean returnValue = productData.updateProduct_list(id, produkt);
        
        // set Category to reload the product list
        selectCategory(currentProduct.getCategory().getId());
        
        return returnValue;
    }
    
    /**
     * This Methode finds the Category to a given id
     * 
     * @param id the id of the Category this Methode searchs for 
     * @return the category to the given id
     */
    public Category findCategoryWithId(int id) {
        LOG.info("[Products] findCategoryWithId => id: " + id);
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
        LOG.info("[Products] billingType => id: " + id);
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
    public List<Product> getCurrentProducts() {
//        LOG.info("[Products] aktuell erstes Product: " + aktuelle_produkt_liste.get(0).getName());
        return currentProducts;
    }

    /**
     * Get Value of currentProduct
     * 
     * @return the value of currentProduct
     */
    public Product getCurrentProduct() {
        return currentProduct;
    }

    /**
     * Set Value of currentProduct
     * 
     * @param currentProduct the new value of currentProduct
     */
    public void setCurrentProduct(Product currentProduct) {
        LOG.info("[Products] current Product: " + currentProduct.getName());
        this.currentProduct = currentProduct;
    }
}
