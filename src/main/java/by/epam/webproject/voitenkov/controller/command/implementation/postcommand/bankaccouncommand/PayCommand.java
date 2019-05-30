package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.CommandResult;
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
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		CommandResult result = new CommandResult(ConfigurationReader
				.getProperty(ConstantConteiner.GO_RESULT_PAGE), true);

		if (req != null && this.getService() != null) {

			try {

				if (this.getService().payOperation(req)) {
					req.getSession().setAttribute(
							ConstantConteiner.IS_SUCCESS_ATTRIB, true);
					result.setForvardAction(false);
					result.setPath("start?command=go_to_result_page");
				}

			} catch (ServiceLevelException e) {

				result.setPath(ConfigurationReader
						.getProperty(ConstantConteiner.GO_TO_PAY_PAGE));

				req.setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
						e.getMessage());
			}
		}
		return result;
	}
}