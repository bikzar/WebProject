package by.epam.webproject.voitenkov.service.implementation;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.DAOFactory;
import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAO;
import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.dao.transaction.TransactionDAO;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.model.entity.enumeration.OperationType;
import by.epam.webproject.voitenkov.service.TransactionService;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         May 11, 2019
 */
public class TransactionServiceImpl implements TransactionService {

	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();

	private static TransactionDAO transactionDAO = DAO_FACTORY
			.getTransactionDAO();
	private static BankAccountDAOImpl bankAccDAO = DAO_FACTORY
			.getBankAccountDAO();

	private static Logger logger = LogManager.getLogger();

	private TransactionServiceImpl() {
	}

	private static class InstanceCreator {
		private static final TransactionServiceImpl INSTANCE = new TransactionServiceImpl();
	}

	public static TransactionServiceImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public boolean payOperation(HttpServletRequest request)
			throws ServiceLevelException {

		if (request != null) {
			String destinationBankAccountIdTmp = request
					.getParameter(ConfigurationReader
							.getProperty(ConstantConteiner.F_BANK_ACCOUNT_ID));

			String sourceCardIdTmp = request.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_CREDIT_CARD_ID));

			String sumTmp = request.getParameter(
					ConfigurationReader.getProperty(ConstantConteiner.SUM));

			if (destinationBankAccountIdTmp != null && sourceCardIdTmp != null
					&& sumTmp != null) {

				long destinationBankAccountId = Long
						.parseLong(destinationBankAccountIdTmp);

				long sourceCardId = Long.parseLong(sourceCardIdTmp);

				double sum = Double.parseDouble(sumTmp);

				try {

					Transaction transaction = null;

					BankAccount destinationAccount = bankAccDAO
							.getById(destinationBankAccountId);

					if (destinationAccount != null) {

						CreditCard sourceCard = DAO_FACTORY.getCreditCardDAO()
								.getById(sourceCardId);

						if (sourceCard != null) {

							BankAccount sourceBankAccount = bankAccDAO
									.getById(sourceCard.getBankAccountId());

							if (Validator.validateTransaction(sourceBankAccount,
									destinationAccount, sum)) {

								makeTransaction(sourceBankAccount,
										destinationAccount, sum);

								transaction = getTransaction(sourceBankAccount,
										destinationAccount, sourceCard,
										OperationType.PAY, sum);

								transactionDAO.save(transaction);
								
							} else {
								throw new ServiceLevelException(
										ConfigurationReader.getProperty(
												ConstantConteiner.PAY_OPERATON_FAIL_MSG));
							}
						}
					} else {
						// serialazible to file
					}

				} catch (DaoException e) {
					logger.error(
							"Try to get Bank Account in payOperation() methood in TransactionServiceImpl class");

					throw new ServiceLevelException(ConfigurationReader
							.getProperty(ConstantConteiner.SOME_PROBLEM_MSG));
				}

			}
		}

		return false;
	}

	private void makeTransaction(BankAccount sourceBankAccount,
			BankAccount destinationAccount, double sum) throws DaoException {
		// here can be your transaction commission

		sourceBankAccount
				.setAccountMoney(sourceBankAccount.getAccountMoney() - sum);

		destinationAccount
				.setAccountMoney(destinationAccount.getAccountMoney() + sum);

		bankAccDAO.update(sourceBankAccount);
		bankAccDAO.update(destinationAccount);
	}

	private Transaction getTransaction(BankAccount sourceBankAccount,
			BankAccount destinationAccount, CreditCard sourceCard,
			OperationType operationType, double sum) {

		return new Transaction(0, sourceBankAccount.getAccountId(),
				destinationAccount.getAccountId(), operationType,
				sourceCard.getCreditCardId(), sum);
	}

}
