/**
 * 
 */
package by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 * May 22, 2019
 */
public class GoToPaymentHistoryPageCommand implements Command {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return ConfigurationReader.getProperty(ConstantConteiner.HISTORY_PAGE);
	}
}
