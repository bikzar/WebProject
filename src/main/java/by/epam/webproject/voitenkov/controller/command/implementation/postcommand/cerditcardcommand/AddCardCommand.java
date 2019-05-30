package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.service.CreditCardService;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 26, 2019
 */
public class AddCardCommand extends AbstractCommand<CreditCardService> {

	public AddCardCommand(CreditCardService service) {
		super(service);
	}

	@Override
	public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

		CommandResult result = new CommandResult(ConfigurationReader
				.getProperty(ConstantConteiner.UNLOCK_CARD_PAGE), true);

		try {

			if(this.getService().addCard(req)) {
				result.setForvardAction(false);
				result.setPath("start?command=GO_TO_UNLOCK_PAGE");
			}
			
		} catch (ServiceLevelException e) {
			
			req.setAttribute(
					ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
					e.getMessage());
		}

		return result;
	}

}
