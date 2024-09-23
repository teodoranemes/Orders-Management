package dao;

import connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GenericDAO is a generic class for accessing the database using reflection techniques.
 * It provides methods to create, edit, delete, and find objects for any table in the database.
 *
 * @param <T> the type of objects that this DAO will manage
 */
public class GenericDAO<T> {

    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class.getName());
    private Class<T> type;

    /**
     * Constructor for GenericDAO.
     *
     * @param type the class type of the objects that this DAO will manage
     */
    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * Creates a new object in the database.
     *
     * @param object the object to be created in the database
     * @return the generated ID of the created object
     */
    public int create(T object) {
        String tableName = type.getSimpleName().toLowerCase();
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i != 0) {
                query.append(", ");
            }
            query.append(fields[i].getName());
        }
        query.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            if (i != 0) {
                query.append(", ");
            }
            query.append("?");
        }
        query.append(")");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatementParameters(preparedStatement, object, fields);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "GenericDAO:create - " + e.getMessage());
        }
        return -1;
    }

    /**
     * Updates an existing object in the database.
     *
     * @param object the object to be updated in the database
     * @return true if the update was successful, false otherwise
     */
    public boolean update(T object) {
        String tableName = type.getSimpleName().toLowerCase();
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (i != 0) {
                query.append(", ");
            }
            query.append(fields[i].getName()).append(" = ?");
        }
        query.append(" WHERE id = ?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            setPreparedStatementParameters(preparedStatement, object, fields);
            preparedStatement.setInt(fields.length + 1, getIdFieldValue(object));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "GenericDAO:update - " + e.getMessage());
        }
        return false;
    }

    /**
     * Deletes an object from the database by ID.
     *
     * @param id the ID of the object to be deleted from the database
     * @return true if the deletion was successful, false otherwise
     */
    public boolean delete(int id) {
        String tableName = type.getSimpleName().toLowerCase();
        String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "GenericDAO:delete - " + e.getMessage());
        }
        return false;
    }

    /**
     * Finds an object in the database by ID.
     *
     * @param id the ID of the object to be found
     * @return the found object, or null if not found
     */
    public T findById(int id) {
        String tableName = type.getSimpleName().toLowerCase();
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createObjectFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "GenericDAO:findById - " + e.getMessage());
        }
        return null;
    }

    /**
     * Gets all objects from the database.
     *
     * @return a list of all objects in the database
     */
    public List<T> getAll() {
        List<T> objects = new ArrayList<>();
        String tableName = type.getSimpleName().toLowerCase();
        String query = "SELECT * FROM " + tableName;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                objects.add(createObjectFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "GenericDAO:getAll - " + e.getMessage());
        }
        return objects;
    }

    private void setPreparedStatementParameters(PreparedStatement preparedStatement, T object, Field[] fields) throws SQLException {
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                preparedStatement.setObject(i + 1, fields[i].get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private int getIdFieldValue(T object) {
        try {
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            return idField.getInt(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private T createObjectFromResultSet(ResultSet resultSet) {
        try {
            T instance = type.getDeclaredConstructor().newInstance();
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(instance, resultSet.getObject(field.getName()));
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
