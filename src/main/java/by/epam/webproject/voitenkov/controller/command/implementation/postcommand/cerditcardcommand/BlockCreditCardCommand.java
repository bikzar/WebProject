package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.service.CreditCardService;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;
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
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String result = ConfigurationReader
				.getProperty(ConstantConteiner.BANK_ACCOUNT_DETAILS_PAGE);

		try {

			this.getService().blockCreditCard(req);

		} catch (ServiceLevelException e) {

			req.setAttribute(
					ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
					e.getMessage());

			result = ConfigurationReader
					.getProperty(ConstantConteiner.ERROR_PAGE);

		}

		return result;
	}

}
