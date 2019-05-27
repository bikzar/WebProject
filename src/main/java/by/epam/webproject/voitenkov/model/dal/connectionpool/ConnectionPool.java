package by.epam.webproject.voitenkov.model.dal.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */
public class ConnectionPool {

	private static final int POOL_SIZE = Integer.parseInt(
			ConfigurationReader.getProperty(ConstantConteiner.POOL_SIZE));

	private static Logger logger = LogManager.getLogger();
	private static BlockingQueue<Connection> connectionPool;

	private ConnectionPool() {

		connectionPool = new LinkedBlockingDeque<Connection>(POOL_SIZE);

		DBConnector dbConnector = DBConnector.getInstance();

		for (int i = POOL_SIZE; i > 0; i--) {

			Connection connection = dbConnector.getConnection();

			if (connection != null) {

				connectionPool.offer(connection);

			} else {

				logger.warn("Can't get connection.");
			}
		}
	}

	private static class InstanceBuilder {
		private static final ConnectionPool INSTANCE = new ConnectionPool();
	}

	/**
	 * @return ConnectionPool instance.
	 * 
	 */
	public static ConnectionPool getInstance(){

		ConnectionPool conPool = InstanceBuilder.INSTANCE;

		if (connectionPool.isEmpty()) {

			logger.warn("Problen with conectionpool initialisation");

		}

		return conPool;
	}

	/**
	 * @return Connection object or null if happened InterruptedException
	 *         inside.
	 */
	public Connection getConnection() {

		Connection connection = null;

		try {
			connection = connectionPool.take();

		} catch (InterruptedException e) {
			logger.warn("Try to get connection in ConnectionPool class.\n" + e);
		}
		
		return connection;
	}

	public void returnConnection(Connection connection) {

		if (connection != null) {
			connectionPool.offer(connection);
		} else {
			connection = DBConnector.getInstance().getConnection();
			connectionPool.offer(connection);
		}

	}

	public void closeConnectionPool() {

		while (!connectionPool.isEmpty()) {
			try {
				Connection connection = connectionPool.take();
				connection.close();
			} catch (InterruptedException e) {
				logger.warn(
						"Try to close connectionPoll in closeConnectionPool method.\n"
								+ e);
			} catch (SQLException e) {
				logger.warn(
						"Try to close connection from connectionPool in method"
								+ " closeConnectionPool from ConnectionPoll class");
			}
		}
	}
}