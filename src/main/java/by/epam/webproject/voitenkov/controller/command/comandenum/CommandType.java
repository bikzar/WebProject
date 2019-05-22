package by.epam.webproject.voitenkov.controller.command.comandenum;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.controller.command.implementation.getcommand.usercommand.LogOutCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.DefaultCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToLogInPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToPayPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToPaymentHistoryPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToRegistrationPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToReplenishPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.LoadBankAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.PayCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand.BlockCreditCardCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand.CalculateCommissionCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand.LoadHistoryCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand.ReplenishAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.LogInCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.RegistrationCommand;
import by.epam.webproject.voitenkov.service.implementation.BankAccountServiceImpl;
import by.epam.webproject.voitenkov.service.implementation.CreditCardServiceImpl;
import by.epam.webproject.voitenkov.service.implementation.TransactionServiceImpl;
import by.epam.webproject.voitenkov.service.implementation.UserServiceImpl;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public enum CommandType {
	// All CommandType name should be write in Uppercase like the same command
	// in configuration file.
	CALCULATECOMMISSION(new CalculateCommissionCommand(TransactionServiceImpl.getInstance())),
	GOTOPAYMENTHISTORY(new GoToPaymentHistoryPageCommand()),
	GOTOREPLENISHPAGE(new GoToReplenishPageCommand(BankAccountServiceImpl.getInstance())),
	REPLENISHACCOUNT(new ReplenishAccountCommand(TransactionServiceImpl.getInstance())),
	REGISTRATIONPAGE(new GoToRegistrationPageCommand()),
	LOADBANKACCOUNT(new LoadBankAccountCommand(BankAccountServiceImpl.getInstance())),
	LOADPAYHISTORY(new LoadHistoryCommand(TransactionServiceImpl.getInstance())),
	GOTOLOGINPAGE(new GoToLogInPageCommand(UserServiceImpl.getInstance())), 
	REGISTRATION(new RegistrationCommand(UserServiceImpl.getInstance())),
	GOTOPAYPAGE(new GoToPayPageCommand(BankAccountServiceImpl.getInstance())),
	BLOCKCARD(new BlockCreditCardCommand(CreditCardServiceImpl.getInstance())),
	DEFAULT(new DefaultCommand()),
	LOGOUT(new LogOutCommand(UserServiceImpl.getInstance())),
	LOGIN(new LogInCommand(UserServiceImpl.getInstance())),
	PAY(new PayCommand(TransactionServiceImpl.getInstance()));
	
	private Command command;

	private CommandType(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return this.command;
	}

}
