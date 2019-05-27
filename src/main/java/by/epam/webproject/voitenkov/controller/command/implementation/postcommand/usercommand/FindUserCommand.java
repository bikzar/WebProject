package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.service.UserService;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 23, 2019
 */
public class FindUserCommand extends AbstractCommand<UserService> {

	public FindUserCommand(UserService service) {
		super(service);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String nextPage = ConfigurationReader
				.getProperty(ConstantConteiner.SEARCH_PAGE);

		try {

			this.getService().findUser(req);

		} catch (ServiceLevelException e) {
			
			req.setAttribute(
					ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
					e.getMessage());
		}

		return nextPage;
	}

}
