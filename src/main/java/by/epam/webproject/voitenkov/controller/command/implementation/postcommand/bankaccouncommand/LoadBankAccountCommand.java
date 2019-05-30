package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.model.service.BankAccountService;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 7, 2019
 */
public class LoadBankAccountCommand
		extends AbstractCommand<BankAccountService> {

	public LoadBankAccountCommand(BankAccountService service) {
		super(service);
	}

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		CommandResult result = new CommandResult(ConfigurationReader
				.getProperty(ConstantConteiner.BANK_ACCOUNT_DETAILS_PAGE),
				true);

		if (req != null && this.getService() != null) {

			try {

				this.getService().loadBankAccount(req);

			} catch (ServiceLevelException e) {

				req.setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.REQUEST_ERROR_ATTRIBUTE_NAME),
						e.getMessage());

				result = new CommandResult(ConfigurationReader
						.getProperty(ConstantConteiner.ERROR_PAGE), true);

			}
		}
		return result;
	}
}