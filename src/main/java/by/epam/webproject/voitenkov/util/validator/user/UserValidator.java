package by.epam.webproject.voitenkov.util.validator.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.model.dal.dao.DAOFactory;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.dal.dao.user.UserDAOImpl;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.DataMatcher;
import by.epam.webproject.voitenkov.util.validator.user.uservalidatorexception.DuplicateUserLoginException;

/**
 * @author Sergey Voitenkov
 *
 *         May 27, 2019
 */
public class UserValidator {

	private static Logger logger = LogManager.getLogger();

	private UserValidator() {
	}

	public static boolean validateSimpleText(String text) {

		boolean result = false;

		if (text != null && !text.isEmpty()) {

			String regex = ConfigurationReader
					.getProperty(ConstantConteiner.SIMPLE_TEXT_PATTERN);

			result = DataMatcher.match(regex, text);
		}

		return result;
	}

	public static boolean validatePassword(String password) {

		boolean result = false;

		if (password != null && !password.isEmpty()) {

			String regex = ConfigurationReader
					.getProperty(ConstantConteiner.PASSWORD_PATTERN);

			result = DataMatcher.match(regex, password);

		}

		return result;
	}

	public static boolean validateLoggin(String login)
			throws DaoException, DuplicateUserLoginException {

		boolean result = false;

		if (login != null && !login.isEmpty()) {
			String regex = ConfigurationReader
					.getProperty(ConstantConteiner.LOGGIN_PATTERN);

			result = DataMatcher.match(regex, login);

			if (result) {
				UserDAOImpl userDao = DAOFactory.getInstance().getUserDAO();
				try {

					if (userDao.getUserIDByLogin(login) > 0) {

						throw new DuplicateUserLoginException(
								ConfigurationReader.getProperty(
										ConstantConteiner.DUPLICATE_USER_LOGIN_MSG));
					}

				} catch (DaoException e) {
					logger.error(
							"Try to getUserIdByLogin in validateLoggin() methood UserValidator calss\n"
									+ e.getStackTrace());
					throw e;
				}
			}

		}

		return result;
	}

	public static boolean validateBirthData(LocalDate birthDate) {

		final long defMaxHumanLife = 120;
		final long defComingOfAge = 18;

		boolean result = false;

		String maxHumanLifeTmp = ConfigurationReader
				.getProperty(ConstantConteiner.MAX_HUMAN_LIFE);

		String comingOfAgeTmp = ConfigurationReader
				.getProperty(ConstantConteiner.COMING_OF_AGE);

		long maxHumanLife = maxHumanLifeTmp != null
				&& !maxHumanLifeTmp.isEmpty() ? Long.parseLong(maxHumanLifeTmp)
						: defMaxHumanLife;

		long comingOfAge = comingOfAgeTmp != null && !comingOfAgeTmp.isEmpty()
				? Long.parseLong(comingOfAgeTmp)
				: defComingOfAge;

		LocalDate now = LocalDate.now();
		LocalDate maxDate = now.minusYears(maxHumanLife);
		LocalDate minDate = now.minusYears(comingOfAge);

		if (birthDate != null && birthDate.isAfter(maxDate)
				&& birthDate.isBefore(minDate)) {
			result = true;
		}

		return result;
	}

	/**
	 * @param name
	 * @param secondName
	 * @param login
	 * @param password
	 * @param bDate
	 * @return Empty list if all is ok. Or not empty list with all
	 *         exceptions is happened inside
	 * @throws DaoException
	 * @throws DuplicateUserLoginException
	 */
	public static List<String> validateUserParameters(String name,
			String secondName, String login, String password, LocalDate bDate)
			throws DaoException{

		List<String> exceptionList = new ArrayList<String>();

		if (!validateSimpleText(name)) {
			exceptionList.add(ConfigurationReader
					.getProperty(ConstantConteiner.INVALID_NAME_MSG));
		}

		if (!validateSimpleText(secondName)) {
			exceptionList.add(ConfigurationReader
					.getProperty(ConstantConteiner.INVALID_SECONDNAME_MSG));
		}

		if (!validatePassword(password)) {
			exceptionList.add(ConfigurationReader
					.getProperty(ConstantConteiner.INVALID_PASSWORD_MSG));
		}

		if (!validateBirthData(bDate)) {
			exceptionList.add(ConfigurationReader
					.getProperty(ConstantConteiner.INVALID_BDATE_MSG));
		}

		try {
			
			if (!validateLoggin(login)) {
				exceptionList.add(ConfigurationReader
						.getProperty(ConstantConteiner.INVALID_LOGIN_MSG));
			}
			
		} catch (DuplicateUserLoginException e) {
			exceptionList.add(e.getMessage());
		}

		return exceptionList;
	}
}
