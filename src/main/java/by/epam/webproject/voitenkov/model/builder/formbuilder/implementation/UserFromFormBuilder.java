package by.epam.webproject.voitenkov.model.builder.formbuilder.implementation;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.model.builder.formbuilder.FromFormBuilder;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 25, 2019
 */
public class UserFromFormBuilder implements FromFormBuilder<User> {

	private static Logger logger = LogManager.getFormatterLogger();

	/**
	 * @return User object without userId and empty bankAccountList.
	 */
	@Override
	public User build(HttpServletRequest req) {

		long userId = 0;
		String name = null;
		String secondName = null;
		LocalDate birthDate = null;
		boolean isAdmin = false;
		String login = null;
		String password = null;
		ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();

		if (req != null) {

			try {

				String temp;

				temp = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_USER_NAME));

				name = encodeString(temp);

				temp = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_USER_SECOND_NAME));

				secondName = encodeString(temp);

				temp = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_LOGIN));

				login = encodeString(temp);

				temp = req.getParameter(ConfigurationReader
						.getProperty(ConstantConteiner.F_PASSWORD));

				password = encodeString(temp);

			} catch (UnsupportedEncodingException e) {
				logger.warn(
						"Try to encode in build() methood UserFromFormBuilder class. Unknown encoding: "
								+ ConstantConteiner.UTF_8_ENCODING);
			}

			birthDate = LocalDate.parse(req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_BIRTH_DATE)));
		}

		return new User(userId, name, secondName, birthDate, isAdmin, login,
				password, bankAccountList);
	}

	private String encodeString(String text)
			throws UnsupportedEncodingException {
		return new String(text.getBytes(StandardCharsets.ISO_8859_1),
				ConstantConteiner.UTF_8_ENCODING);
	}
}
