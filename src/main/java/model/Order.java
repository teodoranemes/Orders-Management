package model;

/**
 * Represents an order placed in the system.
 * This class stores details about an order including identifiers for the order,
 * client, and product, as well as the quantity and price of the product ordered.
 */
public class Order {
    private int id;
    private int id_client;
    private int id_product;
    private int quantity;
    private int price;

    /**
     * Constructs a new Order with specified details.
     *
     * @param id The unique identifier of the order.
     * @param id_client The unique identifier of the client who placed the order.
     * @param id_product The unique identifier of the product ordered.
     * @param quantity The quantity of the product ordered.
     * @param price The total price for the quantity of the product.
     */
    public Order(int id, int id_client, int id_product, int quantity, int price) {
        this.id = id;
        this.id_client = id_client;
        this.id_product = id_product;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the unique identifier of the order.
     *
     * @return the order ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the quantity of the product ordered.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the total price of the order.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the unique identifier of the client who placed the order.
     *
     * @return the client ID
     */
    public int getClientId() {
        return id_client;
    }

    /**
     * Returns the unique identifier of the product ordered.
     *
     * @return the product ID
     */
    public int getProductId() {
        return id_product;
    }
}
