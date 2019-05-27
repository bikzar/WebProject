package by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.Command;
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
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String nextPage = ConfigurationReader
				.getProperty(ConstantConteiner.USER_PAGE);

		if (req != null) {

			User user = (User) req.getSession().getAttribute(
					ConfigurationReader.getProperty(ConstantConteiner.USER));

			if (user != null && user.isAdmin()) {
				nextPage = ConfigurationReader
						.getProperty(ConstantConteiner.ADMIN_PAGE);
			}

			req.getSession().setAttribute(ConstantConteiner.LANGUAGE,
					req.getParameter(ConstantConteiner.LANGUAGE));
		}

		return nextPage;
	}

}
