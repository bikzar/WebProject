package by.epam.webproject.voitenkov.service.implementation;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.DAOFactory;
import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.dao.daoexception.DuplicateUserException;
import by.epam.webproject.voitenkov.dao.user.UserDAOImpl;
import by.epam.webproject.voitenkov.model.builder.formbuilder.FromFormBuilder;
import by.epam.webproject.voitenkov.model.builder.formbuilder.implementation.UserFromFormBuilder;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.service.UserService;
import by.epam.webproject.voitenkov.service.serviceexception.CantRegistredUserException;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.Maker;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 25, 2019
 */
public class UserServiceImpl implements UserService {

	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private static final UserDAOImpl USER_DAO = DAO_FACTORY.getUserDAO();

	private static Logger logger = LogManager.getLogger();

	private static FromFormBuilder<User> builder;

	private UserServiceImpl() {
		builder = new UserFromFormBuilder();
	}

	private static class InstanceCreator {
		private static final UserServiceImpl INSTANCE = new UserServiceImpl();
	}

	public static UserServiceImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public boolean registration(HttpServletRequest req)
			throws CantRegistredUserException, DuplicateUserException {

		boolean result = false;

		if (req != null) {

			User user = builder.build(req);

			try {

				result = USER_DAO.save(user);

				req.getSession().setAttribute(
						ConfigurationReader.getProperty(ConstantConteiner.USER),
						user);

			} catch (DaoException e) {

				throw new CantRegistredUserException(e.getMessage());
			}
		}

		return result;
	}

	@Override
	public boolean logIn(HttpServletRequest req) throws DaoException {

		boolean result = false;

		if (req != null) {

			String login = req.getParameter(
					ConfigurationReader.getProperty(ConstantConteiner.F_LOGIN));

			String password = req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_PASSWORD));

			String passwordTemp = USER_DAO.getUserPassword(login);

			if (passwordTemp != null) {
				result = passwordTemp.equals(password);
			}

			if (result) {

				User user = USER_DAO.getById(USER_DAO.getUserIDByLogin(login));

				req.getSession().setAttribute(
						ConfigurationReader.getProperty(ConstantConteiner.USER),
						user);

				BankAccountDAOImpl bankDAO = DAO_FACTORY.getBankAccountDAO();

				List<BankAccount> bankAccountList = bankDAO
						.getAll(user.getUserId());

				req.getSession().setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.BANK_ACCOUNT_LIST),
						bankAccountList);
			}
		}

		return result;
	}

	@Override
	public void logOut(HttpServletRequest req) {

		HttpSession curruntSession = req.getSession(false);

		if (curruntSession != null) {
			curruntSession.invalidate();
		}
	}

	@Override
	public void findUser(HttpServletRequest req) throws ServiceLevelException {

		if (req != null) {

			try {
				String name = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_USER_NAME));

				name = encodeString(name);

				String secondName = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_USER_SECOND_NAME));

				secondName = encodeString(secondName);

				String login = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_LOGIN));

				login = encodeString(login);

				if (name != null && !name.isEmpty() && secondName != null
						&& !secondName.isEmpty()) {

					try {

						List<User> list = USER_DAO.getUserList(name, secondName,
								login);
						
						req.setAttribute(
								ConfigurationReader.getProperty(
										ConstantConteiner.F_USER_NAME),
								name);

						req.setAttribute(ConfigurationReader.getProperty(
								ConstantConteiner.F_USER_SECOND_NAME),
								secondName);

						req.setAttribute(ConfigurationReader.getProperty(
								ConstantConteiner.F_LOGIN), login);

						if (!list.isEmpty()) {
							
							req.setAttribute(ConstantConteiner.USER_LIST, list);

							req.setAttribute(ConfigurationReader.getProperty(
									ConstantConteiner.IS_AFTER_CALCULATION),
									true);
						} else {

							throw new ServiceLevelException(
									ConfigurationReader.getProperty(
											ConstantConteiner.CANT_FIND_USER_MSG));
						}

					} catch (DaoException e) {
						logger.error(
								"Try to execute getUserListByQuery() methood in UserServiceImpl class"
										+ e);
						throw new ServiceLevelException(ConfigurationReader
								.getProperty(ConstantConteiner.DB_PROBLEM_MSG));
					}

				} else {
					throw new ServiceLevelException(ConfigurationReader
							.getProperty(ConstantConteiner.INCORRECT_DATA_MSG));
				}

			} catch (UnsupportedEncodingException e1) {
				throw new ServiceLevelException(ConfigurationReader
						.getProperty(ConstantConteiner.SOME_PROBLEM_MSG));
			}

		}

	}

	private String encodeString(String text)
			throws UnsupportedEncodingException {
		return new String(text.getBytes(StandardCharsets.ISO_8859_1),
				ConstantConteiner.UTF_8_ENCODING);
	}
}