package by.epam.webproject.voitenkov.controller.command.comandenum;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.controller.command.implementation.getcommand.usercommand.LogOutCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.*;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.LoadBankAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.PayCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.ReplenishAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand.BlockCreditCardCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.LogInCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.RegistrationCommand;
import by.epam.webproject.voitenkov.service.implementation.*;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public enum CommandType {
	// All CommandType name should be write in Uppercase like the same command
	// in configuration file.
	REPLENISHACCOUNT(new ReplenishAccountCommand()),
	REGISTRATIONPAGE(new GoToRegistrationPageCommand()),
	LOADBANKACCOUNT(new LoadBankAccountCommand()),
	GOTOLOGINPAGE(new GoToLogInPageCommand(UserServiceImpl.getInstance())), 
	REGISTRATION(new RegistrationCommand(UserServiceImpl.getInstance())),
	GOTOPAYPAGE(new GoToPayPageCommand(BankAccountServiceImpl.getInstance())),
	BLOCKCARD(new BlockCreditCardCommand(CreditCardServiceImpl.getInstance())),
	DEFAULT(new DefaultCommand()),
	LOGOUT(new LogOutCommand(UserServiceImpl.getInstance())),
	LOGIN(new LogInCommand(UserServiceImpl.getInstance())),
	PAY(new PayCommand(BankAccountServiceImpl.getInstance()));

	private Command command;

	private CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return this.command;
	}

}
