package by.epam.webproject.voitenkov.model.dbconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import by.epam.webproject.voitenkov.dao.connectionpool.ConnectionPool;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 16, 2019
 */
class ConnectionPoolTest {

	@Test
	void getInstance() {

		ConnectionPool conPool;
		Connection connection = null;

		try {
			conPool = ConnectionPool.getInstance();
			connection = conPool.getConnection();
			assertEquals(true, connection.isValid(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}