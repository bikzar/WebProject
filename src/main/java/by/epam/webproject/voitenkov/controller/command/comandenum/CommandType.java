package by.epam.webproject.voitenkov.controller.command.comandenum;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.controller.command.implementation.getcommand.usercommand.LogOutCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.ChangeLanguageComand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.DefaultCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToLogInPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToPayPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToPaymentHistoryPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToRegistrationPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToReplenishPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToResultPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.GoToUnlockPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.gotopagecommand.LoadSearchFormCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.LoadBankAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.LockBankAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.PayCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.bankaccouncommand.UnLockBankAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand.AddCardCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand.BlockCreditCardCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand.DeleteCardCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.cerditcardcommand.UnLockCardCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand.CalculateCommissionCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand.LoadHistoryCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.transactioncommand.ReplenishAccountCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.FindUserCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.LoadUnBlockPageCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.LogInCommand;
import by.epam.webproject.voitenkov.controller.command.implementation.postcommand.usercommand.RegistrationCommand;
import by.epam.webproject.voitenkov.model.service.implementation.BankAccountServiceImpl;
import by.epam.webproject.voitenkov.model.service.implementation.CreditCardServiceImpl;
import by.epam.webproject.voitenkov.model.service.implementation.TransactionServiceImpl;
import by.epam.webproject.voitenkov.model.service.implementation.UserServiceImpl;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public enum CommandType {
	// All CommandType name should be write in Uppercase like the same command
	// on jsp page.
	CALCULATE_COMMISSION(new CalculateCommissionCommand(TransactionServiceImpl.getInstance())),
	GO_TO_PAYMENT_HISTORY(new GoToPaymentHistoryPageCommand()),
	GO_TO_REPLENISH_PAGE(new GoToReplenishPageCommand(BankAccountServiceImpl.getInstance())),
	REPLENISH_ACCOUNT(new ReplenishAccountCommand(TransactionServiceImpl.getInstance())),
	REGISTRATION_PAGE(new GoToRegistrationPageCommand()),
	LOAD_BANK_ACCOUNT(new LoadBankAccountCommand(BankAccountServiceImpl.getInstance())),
	GO_TO_RESULT_PAGE(new GoToResultPageCommand()),
	GO_TO_UNLOCK_PAGE(new GoToUnlockPageCommand()),
	LOAD_UNLOCK_PAGE(new LoadUnBlockPageCommand(BankAccountServiceImpl.getInstance())),
	LOAD_SEARCH_FORM(new LoadSearchFormCommand()),
	LOAD_PAY_HISTORY(new LoadHistoryCommand(TransactionServiceImpl.getInstance())),
	GO_TO_LOGIN_PAGE(new GoToLogInPageCommand(UserServiceImpl.getInstance())), 
	UNLOCK_ACCOUNT(new UnLockBankAccountCommand(BankAccountServiceImpl.getInstance())),
	GO_TO_PAY_PAGE(new GoToPayPageCommand(BankAccountServiceImpl.getInstance())),
	CHANGE_LOCALE(new ChangeLanguageComand()),
	REGISTRATION(new RegistrationCommand(UserServiceImpl.getInstance())),
	LOCK_ACCOUNT(new LockBankAccountCommand(BankAccountServiceImpl.getInstance())),
	UNLOCK_CARD(new UnLockCardCommand(CreditCardServiceImpl.getInstance())),
	DELETE_CARD(new DeleteCardCommand(CreditCardServiceImpl.getInstance())),
	LOCK_CARD(new BlockCreditCardCommand(CreditCardServiceImpl.getInstance())),
	FIND_USER(new FindUserCommand(UserServiceImpl.getInstance())),
	DEFAULT(new DefaultCommand()),
	ADD_CARD(new AddCardCommand(CreditCardServiceImpl.getInstance())),
	LOG_OUT(new LogOutCommand(UserServiceImpl.getInstance())),
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
