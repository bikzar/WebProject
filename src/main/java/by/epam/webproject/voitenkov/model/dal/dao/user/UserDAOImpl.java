package by.epam.webproject.voitenkov.model.dal.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatementBuilderFactory;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementUserBuilder;
import by.epam.webproject.voitenkov.model.dal.dao.AbstractDAO;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 26, 2019
 */
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	private static Logger logger = LogManager.getLogger();
	private FromStatementUserBuilder builder;

	private UserDAOImpl() {
		builder = FromStatementBuilderFactory.getInstance().getUserBuilder();
	}

	private static class InstanceCreator {
		private static final UserDAOImpl INSTANCE = new UserDAOImpl();
	}

	public static UserDAOImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public int getUserIDByLogin(String userLogin) throws DaoException {

		Statement statement = null;
		int userID = -1;

		if (userLogin != null) {

			try (ResultSet resSet = getResultSet(
					ConfigurationReader.getProperty(
							ConstantConteiner.GET_USER_ID_BY_LOGIN),
					userLogin)) {

				if (resSet != null) {
					resSet.next();

					userID = resSet.getInt(ConfigurationReader
							.getProperty(ConstantConteiner.DB_USER_ID));

					statement = resSet.getStatement();
				}
			} catch (SQLException e) {

				logger.warn(
						"A database access error occurs or method prepareStatement() "
								+ "(getUserIDByLogin() in UserDAO calss) is called on a closed connection");

				throw new DaoException(ConfigurationReader
						.getProperty(ConstantConteiner.DB_PROBLEM_MSG));

			} finally {

				try {

					if (statement != null) {
						statement.close();
					}

				} catch (SQLException e) {
					logger.warn(
							"SQLException when try to close statemant or resultSet in getUserIDByLogin() methood UserDAOImpl class");
				}
			}

		}

		return userID;
	}

	@Override
	public String getUserPassword(String userLogin) throws DaoException {

		Statement statement = null;
		String password = null;

		if (userLogin != null) {
			try (ResultSet resultSet = getResultSet(ConfigurationReader
					.getProperty(ConstantConteiner.GET_USER_PASWORD),
					userLogin)) {

				if (resultSet != null) {

					resultSet.next();

					password = resultSet.getString(ConfigurationReader
							.getProperty(ConstantConteiner.DB_USER_PASSWORD));

					statement = resultSet.getStatement();
				}

			} catch (SQLException e) {

				logger.warn("A database access error occurs or statement code "
						+ "block (getUserPassword() in UserDAO calss) is called "
						+ "on a closed connection");

				throw new DaoException(ConfigurationReader
						.getProperty(ConstantConteiner.DB_PROBLEM_MSG));

			} finally {
				try {

					if (statement != null) {
						statement.close();
					}

				} catch (SQLException e) {
					logger.warn("SQLException when try to close statemant or "
							+ "resultSet in getUserPassword() methood UserDAOImpl class");
				}
			}
		}

		return password;
	}

	@Override
	public User getById(long id) throws DaoException {

		User result = null;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.GET_USER_BY_ID);

		if (Validator.validateID(id)) {
			result = executeSingleResultQuery(query, builder, id);
		}

		return result;
	}

	@Override
	public List<User> getAll(long id) throws DaoException {

		ArrayList<User> list = null;

		if (Validator.validateID(id)) {
			list = new ArrayList<User>();
			list.add(getById(id));
		}

		return list;
	}

	@Override
	public boolean save(User entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.SAVE_USER);

		if (entity != null) {

			Object[] userElements = { entity.getName(), entity.getSecondName(),
					entity.getBirthDate(), ConstantConteiner.NOT_ADMIN,
					entity.getLogin(), entity.getPassword() };

			result = executeUpdateQuery(query, userElements);
		}

		return result;
	}

	@Override
	public boolean update(User entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.UPDATE_USER);

		if (entity != null) {

			Object[] userElements = { entity.getName(), entity.getSecondName(),
					entity.getBirthDate()
							.format(DateTimeFormatter.ISO_LOCAL_DATE),
					entity.isAdmin(), entity.getLogin(), entity.getPassword(),
					entity.getUserId() };

			result = executeUpdateQuery(query, userElements);
		}

		return result;
	}

	@Override
	public boolean delete(User entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.DELETE_USER_BY_ID);

		if (entity != null) {

			result = executeUpdateQuery(query, entity.getUserId());
		}
		return result;
	}

	@Override
	public List<User> getUserList(String name, String secondName, String login)
			throws DaoException {

		ArrayList<User> list = null;

		if (name != null && !name.isEmpty()
				|| secondName != null && !secondName.isEmpty()) {

			list = new ArrayList<User>();

			String query = ConfigurationReader
					.getProperty(ConstantConteiner.FIND_USER_FOR_ADMIN);

			if (login == null || login != null && login.isEmpty()) {
				login = "";
			}

			Object[] elements = { name, secondName, login };

			ResultSet resultSet = getResultSet(query, elements);

			try {

				while (resultSet != null && resultSet.next()) {

					long id = resultSet.getLong(ConfigurationReader
							.getProperty(ConstantConteiner.DB_USER_ID));

					list.add(getById(id));
				}

			} catch (SQLException e) {
				logger.warn(
						"Try to get next() on resultSet getUserListByQuery() methood UserDAOImpl class");

				throw new DaoException(ConfigurationReader
						.getProperty(ConstantConteiner.DB_PROBLEM_MSG));
			}

		}

		return list;
	}

}