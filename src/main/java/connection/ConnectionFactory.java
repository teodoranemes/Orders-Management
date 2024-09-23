package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ConnectionFactory class is responsible for managing the database connection.
 * It provides methods to create and close connections, statements, and result sets.
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "2003TEOvlad";

	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Private constructor for the ConnectionFactory class.
	 * Initializes the JDBC driver.
	 */
	public ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates and returns a new database connection.
	 *
	 * @return a new Connection object
	 */
	private Connection createConnection() {
		try {
			Connection connection = DriverManager.getConnection(DBURL, USER, PASS);
			System.out.println("Connected to Database.");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database", e);
		}
	}

	/**
	 * Returns a database connection from the singleton instance.
	 *
	 * @return a Connection object
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * Closes the given database connection.
	 *
	 * @param connection the Connection to be closed
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection");
			}
		}
	}

	/**
	 * Closes the given statement.
	 *
	 * @param statement the Statement to be closed
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
			}
		}
	}

	/**
	 * Closes the given result set.
	 *
	 * @param resultSet the ResultSet to be closed
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occurred while trying to close the ResultSet");
			}
		}
	}
}
