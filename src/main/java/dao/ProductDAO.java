package dao;

import model.Product;
import connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for handling product-related database operations.
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private ConnectionFactory connectionFactory;

    /**
     * Constructs a ProductDAO with a specified ConnectionFactory.
     *
     * @param connectionFactory the connection factory to be used for obtaining database connections
     */
    public ProductDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Finds a product by its ID.
     *
     * @param productId the ID of the product to find
     * @return the Product object if found, or null if not found
     */
    public Product findById(int productId) {
        String query = "SELECT * FROM product WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findById - " + e.getMessage());
        }
        return null;
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product the Product object to insert
     * @return the ID of the inserted product, or -1 if the insertion failed
     */
    public int insert(Product product) {
        String insertQuery = "INSERT INTO product (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert - " + e.getMessage());
        }
        return -1;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product the Product object with updated values
     * @return true if the update was successful, false otherwise
     */
    public boolean update(Product product) {
        String updateQuery = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update - " + e.getMessage());
        }
        return false;
    }

    /**
     * Deletes a product from the database.
     *
     * @param productId the ID of the product to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean delete(int productId) {
        String deleteQuery = "DELETE FROM product WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, productId);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete - " + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all Product objects
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String findAllQuery = "SELECT * FROM product";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:getAllProducts - " + e.getMessage());
        }
        return products;
    }
}
