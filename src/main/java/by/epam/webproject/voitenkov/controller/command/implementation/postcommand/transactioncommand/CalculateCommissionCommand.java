package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand;

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
 *         May 18, 2019
 */
public class CalculateCommissionCommand
		extends AbstractCommand<TransactionService> {

	public CalculateCommissionCommand(TransactionService service) {
		super(service);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String nextPage = ConfigurationReader
				.getProperty(ConstantConteiner.GO_TO_REPLANISH_PAGE);

		try {
			
			this.getService().calculateCommision(req);
			
		} catch (ServiceLevelException e) {
			
			req.setAttribute(
					ConfigurationReader.getProperty(
							ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
					e.getMessage());
		}

		return nextPage;
	}

}
