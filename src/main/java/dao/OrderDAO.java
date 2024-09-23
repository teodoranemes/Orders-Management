package dao;

import model.Order;
import connection.ConnectionFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for handling order-related database operations.
 */
public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private ConnectionFactory connectionFactory;

    /**
     * Constructs an OrderDAO with a specified ConnectionFactory.
     *
     * @param connectionFactory the connection factory to be used for obtaining database connections
     */
    public OrderDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Inserts a new order into the database.
     *
     * @param order the Order object to insert
     * @return the ID of the inserted order, or -1 if the insertion failed
     */
    public int insert(Order order) {
        String insertQuery = "INSERT INTO order_tab (id, id_client, id_product, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getClientId());
            preparedStatement.setInt(3, order.getProductId());
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setInt(5, order.getPrice());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert - " + e.getMessage());
        }
        return -1;
    }
}
