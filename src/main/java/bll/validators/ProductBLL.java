package bll.validators;

import model.Product;
import dao.ProductDAO;
import java.util.NoSuchElementException;
import java.util.List;

/**
 * This class handles the business logic for products within the application.
 * It interacts with the data access layer (DAO) for product operations.
 */
public class ProductBLL {

    /**
     * DAO object for interacting with the products table in the database.
     */
    private ProductDAO productDAO;

    /**
     * Constructor that takes a reference to a ProductDAO object.
     *
     * @param productDAO DAO object for product operations.
     */
    public ProductBLL(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Finds a product by its ID in the database.
     *
     * This method retrieves a product from the database based on the provided ID.
     * If the product is not found, it throws a NoSuchElementException.
     *
     * @param id The ID of the product to find.
     * @return The Product object with the matching ID, or throws a NoSuchElementException if not found.
     * @throws NoSuchElementException if the product with the specified ID does not exist.
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with ID " + id + " was not found.");
        }
        return product;
    }

    /**
     * Inserts a new product into the database.
     *
     * This method delegates the insertion of a product object to the ProductDAO.
     *
     * @param product The Product object to be inserted.
     * @return The ID of the inserted product, or -1 if insertion failed.
     */
    public int insertProduct(Product product) {
        return productDAO.insert(product);
    }

    /**
     * Updates an existing product in the database.
     *
     * This method delegates the update of a product object to the ProductDAO.
     *
     * @param product The Product object with updated information.
     */
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    /**
     * Deletes a product from the database.
     *
     * This method delegates the deletion of a product based on its ID to the ProductDAO.
     *
     * @param productId The ID of the product to be deleted.
     */
    public void deleteProduct(int productId) {
        productDAO.delete(productId);
    }

    /**
     * Retrieves all products from the database.
     *
     * This method delegates the retrieval of all product objects to the ProductDAO.
     *
     * @return A List containing all Product objects from the database.
     */
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    /**
     * Updates the quantity of a product in the database.
     *
     * This method first retrieves the product by ID, then validates if there's enough quantity available
     * to decrement. If sufficient quantity exists, it updates the product's quantity and persists the change
     * to the database. Otherwise, it throws an IllegalArgumentException.
     *
     * @param productId The ID of the product to update quantity.
     * @param quantityToDecrement The amount by which to decrease the product's quantity.
     * @return True if the quantity was updated successfully, False otherwise.
     * @throws IllegalArgumentException if the requested quantity decrement is greater than the available quantity.
     */
    public boolean updateProductQuantity(int productId, int quantityToDecrement) {
        Product product = findProductById(productId);
        if (product.getQuantity() < quantityToDecrement) {
            throw new IllegalArgumentException("Insufficient quantity available for product ID: " + productId);
        }
        product.setQuantity(product.getQuantity() - quantityToDecrement);
        return productDAO.update(product);
    }
}

