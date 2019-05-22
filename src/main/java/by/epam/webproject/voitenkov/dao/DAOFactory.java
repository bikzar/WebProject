package by.epam.webproject.voitenkov.dao;

import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.creditcard.CreditCardDAOImpl;
import by.epam.webproject.voitenkov.dao.transaction.TransactionDAOImpl;
import by.epam.webproject.voitenkov.dao.user.UserDAOImpl;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 26, 2019
 */
public class DAOFactory {

	private DAOFactory() {
	}

	private static class InstanceCreator {
		private static final DAOFactory INSTANCE = new DAOFactory();
	}

	public static DAOFactory getInstance() {
		return InstanceCreator.INSTANCE;
	}

	public UserDAOImpl getUserDAO() {
		return UserDAOImpl.getInstance();
	}

	public BankAccountDAOImpl getBankAccountDAO() {
		return BankAccountDAOImpl.getInstance();
	}

	public TransactionDAOImpl getTransactionDAO() {
		return TransactionDAOImpl.getInstance();
	}

	public CreditCardDAOImpl getCreditCardDAO() {
		return CreditCardDAOImpl.getInstance();
	}

}
