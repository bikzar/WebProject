package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.BankAccountCommand;
import by.epam.webproject.voitenkov.service.BankAccountService;

/**
 * @author Sergey Voitenkov
 *
 * May 9, 2019
 */
public class ReplenishAccountCommand extends BankAccountCommand {

	public ReplenishAccountCommand() {
	}

	public ReplenishAccountCommand(BankAccountService service) {
		super(service);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		return null;
	}

}
