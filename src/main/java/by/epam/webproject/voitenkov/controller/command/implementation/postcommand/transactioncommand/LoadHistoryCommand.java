/**
 * 
 */
package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand;

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
 *         May 21, 2019
 */
public class LoadHistoryCommand extends AbstractCommand<TransactionService> {

	public LoadHistoryCommand(TransactionService service) {
		super(service);
	}

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		CommandResult result = new CommandResult(
				ConfigurationReader.getProperty(ConstantConteiner.HISTORY_PAGE),
				true);

		if (req != null && this.getService() != null) {

			try {

				this.getService().loadHistory(req);

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
