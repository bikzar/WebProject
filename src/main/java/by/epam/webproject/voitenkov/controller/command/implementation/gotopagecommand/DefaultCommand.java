package by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 24, 2019
 */
public class DefaultCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {
		return new CommandResult(
				ConfigurationReader.getProperty(ConstantConteiner.DEFAULT_PAGE),
				true);
	}

}