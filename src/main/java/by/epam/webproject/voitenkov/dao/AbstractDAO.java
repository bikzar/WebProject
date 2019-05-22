package by.epam.webproject.voitenkov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.connectionpool.ConnectionPool;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public abstract class AbstractDAO<T> implements DAO<T> {

	private static Logger logger = LogManager.getLogger();

	/**
	 * @param query
	 * @param builder
	 * @param parameters
	 * @return Entity list or Empty entity list (if no connection to DB).
	 * @throws DaoException
	 */
	public List<T> executeQueryList(String query,
			FromStatemantBuilder<T> builder, Object... parameters)
			throws DaoException {

		List<T> result = new ArrayList<T>();
		ResultSet resultSet = null;
		Statement statement = null;

		try {

			if (builder != null) {

				resultSet = getResultSet(query, parameters);

				if (resultSet != null) {

					if (resultSet.next()) {

						while (!resultSet.isAfterLast()) {

							T entity = builder.build(resultSet);

							if (entity != null) {
								result.add(entity);
							}
						}
					}
					statement = resultSet.getStatement();
				}
			}

		} catch (SQLException e) {

			logger.error(
					"SQLException try to get preparedStatemen in executeQueryList() methood AbstractDao calss");

			throw new DaoException(e);
		} finally {

			try {

				if (statement != null) {

					statement.close();
				}

			} catch (SQLException e) {
				logger.warn(
						"Can't close preparedStatement in executeQueryList() methood AbstarctDAO class.");
			}
		}

		return result;

	}

	/**
	 * @return Entity or null (if query or builder or parameters is null or
	 *         don't find any result).
	 * @throws DaoException
	 */
	public T executeSingleResultQuery(String query,
			FromStatemantBuilder<T> builder, Object... parameters)
			throws DaoException {

		T result = null;

		List<T> resultList = executeQueryList(query, builder, parameters);

		if (!resultList.isEmpty()) {
			result = resultList.get(0);
		}

		return result;
	}

	public boolean executeUpdateQuery(String query, Object... parameters)
			throws DaoException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		if (query != null && parameters != null && connection != null) {

			try {

				preparedStatement = connection.prepareStatement(query);

				getPrepareStatement(preparedStatement, parameters);

				result = preparedStatement.executeUpdate() > 0;

			} catch (SQLException e) {
				logger.error(
						"SQLException in executeUpdateQuery() methood AbstractDao calss");

				throw new DaoException(e);

			} finally {

				ConnectionPool.getInstance().returnConnection(connection);

				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						logger.warn(
								"Can't close preparedStement in getResultSet() methood AbstarctDAO class for update query.");
					}
				}
			}
		}
		return result;
	}

	/**
	 * @param query
	 * @param parameters
	 * @return ResultSet or null if query is null or parameters is null or
	 *         connection to DB doesn't exist.
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String query, Object... parameters)
			throws SQLException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		if (query != null && parameters != null && connection != null) {

			try {

				preparedStatement = connection.prepareStatement(query);

				getPrepareStatement(preparedStatement, parameters);

				resultSet = preparedStatement.executeQuery();

			} finally {
				ConnectionPool.getInstance().returnConnection(connection);

			}
		}

		return resultSet;
	}

	protected void getPrepareStatement(PreparedStatement statement,
			Object... parameters) throws SQLException {

		if (statement != null && parameters != null) {

			for (int i = 1; i <= parameters.length; i++) {

				statement.setObject(i, parameters[i - 1]);
			}

		}
	}
}
