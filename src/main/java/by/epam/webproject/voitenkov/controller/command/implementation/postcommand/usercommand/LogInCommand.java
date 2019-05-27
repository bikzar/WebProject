package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.model.service.UserService;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public class LogInCommand extends AbstractCommand<UserService> {

	public LogInCommand(UserService userService) {
		super(userService);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String result = ConfigurationReader
				.getProperty(ConstantConteiner.LOGIN_PAGE);

		if (req != null && this.getService() != null) {

			try {

				if (this.getService().logIn(req)) {

					Object userTemp = req.getSession()
							.getAttribute(ConfigurationReader
									.getProperty(ConstantConteiner.USER));

					if (userTemp instanceof User) {

						User user = (User) userTemp;

						if (user.isAdmin()) {
							result = ConfigurationReader
									.getProperty(ConstantConteiner.ADMIN_PAGE);
						} else {
							result = ConfigurationReader
									.getProperty(ConstantConteiner.USER_PAGE);
						}
					}

				} else {

					req.setAttribute(ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
							ConfigurationReader.getProperty(
									ConstantConteiner.INCORRECT_USER_PASSWORD_MSG));
				}

			} catch (DaoException e) {

				result = ConfigurationReader
						.getProperty(ConstantConteiner.ERROR_PAGE);
				req.setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
						e.getMessage());
			}
		}

		return result;
	}

}
