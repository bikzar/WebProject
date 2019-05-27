package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DuplicateUserException;
import by.epam.webproject.voitenkov.model.service.UserService;
import by.epam.webproject.voitenkov.model.service.serviceexception.CantRegistredUserException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public class RegistrationCommand extends AbstractCommand<UserService> {

	public RegistrationCommand(UserService userService) {
		super(userService);
	}

	/**
	 * @return String like new path to page. Default page path if userService or
	 *         request is null.
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String result = ConfigurationReader
				.getProperty(ConstantConteiner.LOGIN_PAGE);

		if (req != null && this.getService() != null) {

			try {

				this.getService().registration(req);

				result = ConfigurationReader
						.getProperty(ConstantConteiner.USER_PAGE);

			} catch (CantRegistredUserException | DuplicateUserException e) {

				req.setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
						e.getMessage());

				result = ConfigurationReader
						.getProperty(ConstantConteiner.GO_REGISTRATION_PAGE);
			}
		}

		return result;
	}
}
