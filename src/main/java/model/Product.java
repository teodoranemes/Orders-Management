package model;

/**
 * Represents a product in the system.
 * This class stores details about a product, including its identifier, name, price, and available quantity.
 */
public class Product {

    private int id;
    private String name;
    private int price;
    private int quantity;

    /**
     * Constructs a new Product with specified attributes.
     *
     * @param id The unique identifier of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param quantity The available quantity of the product in stock.
     */
    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the unique identifier of the product.
     *
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return the product price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the current quantity of the product available in stock.
     *
     * @return the quantity available
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     * This method is used to update the stock level of the product.
     *
     * @param quantity The new quantity to be set for the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
