package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.model.service.CreditCardService;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 9, 2019
 */
public class BlockCreditCardCommand extends AbstractCommand<CreditCardService> {

	public BlockCreditCardCommand(CreditCardService service) {
		super(service);
	}

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		CommandResult result = null;

		if (req != null && this.getService() != null) {

			try {

				this.getService().lockCreditCard(req);

				User user = (User) req.getSession()
						.getAttribute(ConfigurationReader
								.getProperty(ConstantConteiner.USER));

				if (user != null && user.isAdmin()) {

					result = new CommandResult(ConfigurationReader.getProperty(
							ConstantConteiner.UNLOCK_CARD_PAGE), true);

				} else {

					result = new CommandResult(ConfigurationReader.getProperty(
							ConstantConteiner.BANK_ACCOUNT_DETAILS_PAGE), true);
				}

			} catch (ServiceLevelException e) {

				req.setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
						e.getMessage());

			}
		}
		
		return result;
	}
}