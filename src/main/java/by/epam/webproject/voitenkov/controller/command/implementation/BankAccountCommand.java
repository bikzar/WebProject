package by.epam.webproject.voitenkov.controller.command.implementation;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.service.BankAccountService;
import by.epam.webproject.voitenkov.service.implementation.BankAccountServiceImpl;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public abstract class BankAccountCommand implements Command {

	private BankAccountService service;

	{
		service = BankAccountServiceImpl.getInstance();
	}

	public BankAccountCommand() {
	}

	public BankAccountCommand(BankAccountService service) {

		if (service != null) {
			this.service = service;
		}
	}
	
	public BankAccountService getService() {
		return service;
	}
}
