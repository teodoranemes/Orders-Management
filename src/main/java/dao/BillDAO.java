package dao;

import model.Bill;
import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides data access methods for Bill objects.
 * It interacts with the database table named "log" to perform CRUD (Create, Read, Update, Delete) operations on bills.
 */
public class BillDAO {

    /**
     * SQL statement for inserting a new bill record into the database.
     */
    private static final String INSERT_BILL = "INSERT INTO bill (orderId, clientId, productId, quantity, price) VALUES (?, ?, ?, ?, ?)";

    /**
     * SQL statement for selecting all bill records from the database.
     */
    private static final String SELECT_ALL_BILLS = "SELECT * FROM bill";

    /**
     * Inserts a new bill record into the database table "log".
     *
     * This method establishes a connection, prepares a statement, sets the bill's data as parameters,
     * executes the update, and closes resources.
     *
     * @param bill The Bill object to be inserted.
     */
    public void insertBill(Bill bill) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BILL)) {
            preparedStatement.setInt(1, bill.orderId());
            preparedStatement.setInt(2, bill.clientId());
            preparedStatement.setInt(3, bill.productId());
            preparedStatement.setInt(4, bill.quantity());
            preparedStatement.setInt(5, bill.price());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all bill records from the database table "log".
     *
     * This method establishes a connection, prepares a statement, executes the query, iterates through
     * the results to create Bill objects, and closes resources.
     *
     * @return A List of Bill objects representing all bills in the database.
     */
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BILLS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                bills.add(new Bill(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("clientId"),
                        resultSet.getInt("productId"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}


