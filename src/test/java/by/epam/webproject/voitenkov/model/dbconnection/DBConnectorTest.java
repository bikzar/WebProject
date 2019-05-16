package by.epam.webproject.voitenkov.model.dbconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import by.epam.webproject.voitenkov.dao.connectionpool.DBConnector;

class DBConnectorTest {

	@Test
	void getConnection() {

		Connection connection = null;

		DBConnector dbConnector = DBConnector.getInstance();

		connection = dbConnector.getConnection();

		assertEquals(true, connection != null);
	}

}
