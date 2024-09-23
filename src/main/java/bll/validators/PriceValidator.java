package bll.validators;

import model.Product;

/**
 * This class implements the Validator interface and specifically validates the price of a Product object.
 * It ensures that the price is not negative.
 */
public class PriceValidator implements Validator<Product> {

    /**
     * Validates the price of a Product object.
     *
     * This method throws an IllegalArgumentException if the product price is less than zero.
     *
     * @param t The Product object to be validated.
     * @throws IllegalArgumentException if the product price is negative.
     */
    @Override
    public void validate(Product t) {
        if (t.getPrice() < 0) {
            throw new IllegalArgumentException("The Product price is negative!");
        }
    }
}
