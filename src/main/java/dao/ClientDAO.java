package dao;

import model.Client;
import connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for handling client-related database operations.
 */
public class ClientDAO {
    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private ConnectionFactory connectionFactory;

    /**
     * Constructs a ClientDAO with a specified ConnectionFactory.
     *
     * @param connectionFactory the connection factory to be used for obtaining database connections
     */
    public ClientDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Finds a client by their ID.
     *
     * @param clientId the ID of the client to find
     * @return the Client object if found, otherwise null
     */
    public Client findById(int clientId) {
        String query = "SELECT * FROM client WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getInt("age")
                );
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findById - " + e.getMessage());
        }
        return null;
    }

    /**
     * Inserts a new client into the database.
     *
     * @param client the Client object to insert
     * @return the ID of the inserted client, or -1 if the insertion failed
     */
    public int insert(Client client) {
        String insertQuery = "INSERT INTO client (id, name, address, email, age) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setInt(5, client.getAge());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert - " + e.getMessage());
        }
        return -1;
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client the Client object with updated information
     * @return true if the client was successfully updated, otherwise false
     */
    public boolean update(Client client) {
        String updateQuery = "UPDATE client SET name = ?, address = ?, email = ?, age = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setInt(4, client.getAge());
            preparedStatement.setInt(5, client.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update - " + e.getMessage());
        }
        return false;
    }

    /**
     * Deletes a client from the database.
     *
     * @param clientId the ID of the client to delete
     * @return true if the client was successfully deleted, otherwise false
     */
    public boolean delete(int clientId) {
        String deleteQuery = "DELETE FROM client WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, clientId);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete - " + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of all Client objects in the database
     */
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String findAllQuery = "SELECT * FROM client";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getInt("age")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:getAllClients - " + e.getMessage());
        }
        return clients;
    }
}
