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
 *         May 26, 2019
 */
public class LockBankAccountCommand
		extends AbstractCommand<BankAccountService> {

	public LockBankAccountCommand(BankAccountService service) {
		super(service);
	}

	@Override
	public CommandResult execute(HttpServletRequest req,
			HttpServletResponse resp) {

		CommandResult result = new CommandResult(ConfigurationReader
				.getProperty(ConstantConteiner.UNLOCK_CARD_PAGE), true);
		
		if (req != null && this.getService() != null) {

			try {

				this.getService().lockBankAccount(req);

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