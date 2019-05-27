package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.service.TransactionService;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 9, 2019
 */
public class PayCommand extends AbstractCommand<TransactionService> {

	public PayCommand(TransactionService service) {
		super(service);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String nextPage = ConfigurationReader
				.getProperty(ConstantConteiner.USER_PAGE);

		try {
		
			this.getService().payOperation(req);

		} catch (ServiceLevelException e) {

			nextPage = ConfigurationReader
					.getProperty(ConstantConteiner.ERROR_PAGE);

			req.setAttribute(
					ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
					e.getMessage());

		}
		
		return nextPage;
	}
}
