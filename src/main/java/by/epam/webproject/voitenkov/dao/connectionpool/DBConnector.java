package by.epam.webproject.voitenkov.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */

public class DBConnector {

	private static final String DB_URL = ConfigurationReader
			.getProperty("DBUrl");
	private static final String USER_NAME = ConfigurationReader
			.getProperty("DBUserName");
	private static final String PASSWORD = ConfigurationReader
			.getProperty("DBUserPassword");
	private static final String DRIVER = ConfigurationReader
			.getProperty("DBDriver");
	private Connection connection = null;

	private static Logger logger = LogManager.getLogger();

	private DBConnector() {
	}

	private static class InstanceProvider {
		private static final DBConnector INSTANCE = new DBConnector();
	}

	public static DBConnector getInstance() {
		return InstanceProvider.INSTANCE;
	}

	/**
	 * @return null - only if in method is happened Exception
	 */
	public Connection getConnection() {

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER_NAME,
					PASSWORD);
		} catch (ClassNotFoundException e) {
			logger.warn("Try to load class " + DRIVER + "\n\n" + e);
			
		} catch (SQLException e) {
			logger.warn("Connection failed with paramets:\nurl: " + DB_URL
					+ "\nuser name: " + USER_NAME + "\nuser password: "
					+ PASSWORD + "\nDB driver: " + DRIVER + "\n\n" + e);
		}

		return connection;
	}

}
