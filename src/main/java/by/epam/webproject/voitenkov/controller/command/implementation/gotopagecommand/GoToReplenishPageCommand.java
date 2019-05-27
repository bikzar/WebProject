package by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.service.BankAccountService;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 * May 18, 2019
 */
public class GoToReplenishPageCommand extends AbstractCommand<BankAccountService> {
	
	public GoToReplenishPageCommand(BankAccountService service) {
		super(service);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String page = ConfigurationReader
				.getProperty(ConstantConteiner.GO_TO_REPLANISH_PAGE);

		try {

			this.getService().loadPayForm(req);

		} catch (ServiceLevelException e) {

			page = ConfigurationReader
					.getProperty(ConstantConteiner.ERROR_PAGE);

			req.setAttribute(
					ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
					e.getMessage());
		}

		return page;
	}

}
