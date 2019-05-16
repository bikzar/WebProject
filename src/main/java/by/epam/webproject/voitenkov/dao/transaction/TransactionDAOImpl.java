package by.epam.webproject.voitenkov.dao.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.AbstractDAO;
import by.epam.webproject.voitenkov.dao.connectionpool.ConnectionPool;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 10, 2019
 */
public class TransactionDAOImpl extends AbstractDAO<Transaction>
		implements TransactionDAO {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean save(Transaction transaction) {

		boolean result = false;

		if (transaction != null) {

			ConnectionPool connectionPool = ConnectionPool.getInstance();

			Connection connection = connectionPool.getConnection();

			if (connection != null) {

				try (PreparedStatement preparedStatement = connection
						.prepareStatement(ConfigurationReader.getProperty(
								ConstantConteiner.SAVE_TRANSACTION))) {

					try {
						preparedStatement.setLong(1, transaction.getResursId());
						preparedStatement.setLong(2,
								transaction.getDestinationId());
						preparedStatement.setLong(3,
								transaction.getOperationType().ordinal() + 1);
						preparedStatement.setBoolean(4, true);
						preparedStatement.setLong(5, transaction.getCardId());
						preparedStatement.setDouble(6, transaction.getSum());

						result = preparedStatement.execute();

					} catch (SQLException e) {
						logger.warn(
								"PreparedStatement problem in save() methood TransactionDAOImpl calss");
					}

				} catch (SQLException e) {
					logger.warn(
							"Autoclose PreparedStatement in save() methood TransactionDAOImpl calss");
				}
			}
		}

		return result;
	}

	
	@Override
	public Transaction getById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAll(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Transaction entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Transaction entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
