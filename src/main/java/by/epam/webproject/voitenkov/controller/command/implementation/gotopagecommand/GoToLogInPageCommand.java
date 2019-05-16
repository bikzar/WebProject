package by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.service.UserService;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 * May 9, 2019
 */
public class GoToLogInPageCommand extends AbstractCommand<UserService> {
	
	public GoToLogInPageCommand(UserService userService) {
		super(userService);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return ConfigurationReader.getProperty(ConstantConteiner.LOGIN_PAGE);
	}

}
