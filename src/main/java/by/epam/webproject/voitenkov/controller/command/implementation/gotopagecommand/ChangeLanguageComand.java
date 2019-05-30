package by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 23, 2019
 */
public class ChangeLanguageComand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		CommandResult result = new CommandResult(
				ConfigurationReader.getProperty(ConstantConteiner.USER_PAGE),
				true);

		if (req != null) {

			User user = (User) req.getSession().getAttribute(
					ConfigurationReader.getProperty(ConstantConteiner.USER));

			if (user != null && user.isAdmin()) {
				result = new CommandResult(ConfigurationReader
						.getProperty(ConstantConteiner.ADMIN_PAGE), true);
			}

			req.getSession().setAttribute(ConstantConteiner.LANGUAGE,
					req.getParameter(ConstantConteiner.LANGUAGE));
		}

		return result;
	}
}