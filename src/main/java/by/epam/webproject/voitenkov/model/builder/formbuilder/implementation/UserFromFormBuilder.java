package by.epam.webproject.voitenkov.model.builder.formbuilder.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.webproject.voitenkov.model.builder.builderexception.CantBuildException;
import by.epam.webproject.voitenkov.model.builder.formbuilder.FromFormBuilder;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.Encoder;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.user.UserValidator;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 25, 2019
 */
public class UserFromFormBuilder implements FromFormBuilder<User> {

	/**
	 * @return User object without userId and empty bankAccountList.
	 * @throws CantBuildException
	 */
	@Override
	public User build(HttpServletRequest req) throws CantBuildException {

		long userId = 0;
		String name = null;
		String secondName = null;
		LocalDate birthDate = null;
		boolean isAdmin = false;
		String login = null;
		String password = null;
		List<String> list = new ArrayList<String>();
		ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();

		if (req != null) {

			HttpSession session = req.getSession();

			name = Encoder.encodeToUTF8(req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_USER_NAME)));
			
			session.setAttribute(ConfigurationReader
					.getProperty(ConstantConteiner.F_USER_NAME), name);

			secondName = Encoder.encodeToUTF8(
					req.getParameter(ConfigurationReader.getProperty(
							ConstantConteiner.F_USER_SECOND_NAME)));
			
			session.setAttribute(ConfigurationReader
					.getProperty(ConstantConteiner.F_USER_SECOND_NAME), secondName);

			login = Encoder.encodeToUTF8(req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_LOGIN)));

			session.setAttribute(ConfigurationReader
					.getProperty(ConstantConteiner.F_LOGIN), login);
			
			password = Encoder.encodeToUTF8(req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_PASSWORD)));
			
			session.setAttribute(ConfigurationReader
					.getProperty(ConstantConteiner.F_PASSWORD), password);

			String bDateTemp = req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_BIRTH_DATE));
			
			session.setAttribute(ConfigurationReader
					.getProperty(ConstantConteiner.F_BIRTH_DATE), bDateTemp);

			if (bDateTemp != null && !bDateTemp.isEmpty()) {

				birthDate = LocalDate.parse(bDateTemp);
			}

			try {

				list = UserValidator.validateUserParameters(name, secondName,
						login, password, birthDate);

				if (!list.isEmpty()) {

					req.setAttribute(ConstantConteiner.ERROR_LIST, list);

					throw new CantBuildException();
				}

			} catch (DaoException e) {
				throw new CantBuildException(e.getMessage());
			}
		}

		return new User(userId, name, secondName, birthDate, isAdmin, login,
				password, bankAccountList);

	}
}
