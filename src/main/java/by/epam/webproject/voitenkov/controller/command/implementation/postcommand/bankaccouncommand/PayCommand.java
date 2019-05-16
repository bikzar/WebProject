package by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.implementation.AbstractCommand;
import by.epam.webproject.voitenkov.service.BankAccountService;

/**
 * @author Sergey Voitenkov
 *
 * May 9, 2019
 */
public class PayCommand extends AbstractCommand<BankAccountService>{

	public PayCommand(BankAccountService service) {
		super(service);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
		
		return null;
	}

}
