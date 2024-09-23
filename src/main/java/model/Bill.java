package model;

/**
 * Represents an immutable bill record for an order.
 * This record is used to store transaction details in a log table.
 */
public record Bill(

        /**
         * The order ID associated with this bill.
         */
        int orderId,

        /**
         * The client ID associated with this bill.
         */
        int clientId,

        /**
         * The product ID associated with this bill.
         */
        int productId,

        /**
         * The quantity of the product ordered.
         */
        int quantity,

        /**
         * The total price of the order.
         */
        int price) {

    /**
     * Constructs a new Bill with specified details.
     *
     * @param orderId The unique identifier of the order associated with this bill.
     * @param clientId The unique identifier of the client who made the order.
     * @param productId The unique identifier of the product ordered.
     * @param quantity The quantity of the product ordered.
     * @param totalPrice The total price calculated for the quantity of the product.
     */
}
