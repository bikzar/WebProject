package by.epam.webproject.voitenkov.controller.command.implementation.getcommand.usercommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.service.UserService;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public class LogOutCommand extends AbstractCommand<UserService> {

	public LogOutCommand(UserService userService) {
		super(userService);
	}

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		if (this.getService() != null) {

			this.getService().logOut(req);

		}

		return new CommandResult(
				ConfigurationReader.getProperty(ConstantConteiner.LOGIN_PAGE),
				true);
	}
}