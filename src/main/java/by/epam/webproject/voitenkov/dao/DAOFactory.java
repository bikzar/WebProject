package by.epam.webproject.voitenkov.dao;

import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.creditcard.CreditCardDAOImpl;
import by.epam.webproject.voitenkov.dao.transaction.TransactionDAO;
import by.epam.webproject.voitenkov.dao.transaction.TransactionDAOImpl;
import by.epam.webproject.voitenkov.dao.user.UserDAOImpl;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 26, 2019
 */
public class DAOFactory {

	private TransactionDAO transactionDAO = new TransactionDAOImpl();

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

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
	
	public CreditCardDAOImpl getCreditCardDAO() {
		return CreditCardDAOImpl.getInstance();
	}

}
