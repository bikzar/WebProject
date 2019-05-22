package by.epam.webproject.voitenkov.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.DAOFactory;
import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.creditcard.CreditCardDAOImpl;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.dao.transaction.TransactionDAOImpl;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.model.entity.enumeration.OperationType;
import by.epam.webproject.voitenkov.service.TransactionService;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.Calculator;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.ExchangeCourseManager;
import by.epam.webproject.voitenkov.util.Maker;
import by.epam.webproject.voitenkov.util.OutputTransactionFileMaker;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         May 11, 2019
 */
public class TransactionServiceImpl implements TransactionService {

	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();

	private static TransactionDAOImpl transactionDAO = DAO_FACTORY
			.getTransactionDAO();
	private static BankAccountDAOImpl bankAccDAO = DAO_FACTORY
			.getBankAccountDAO();
	private static CreditCardDAOImpl cardDAO = DAO_FACTORY.getCreditCardDAO();

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

			if (destinationBankAccountIdTmp != null
					&& sourceCardIdTmp != null) {

				long destinationBankAccountId = Long
						.parseLong(destinationBankAccountIdTmp);

				long sourceCardId = Long.parseLong(sourceCardIdTmp);

				double sum = getSum(request);

				try {

					BankAccount sourceBankAccount = null;

					BankAccount destinationAccount = bankAccDAO
							.getById(destinationBankAccountId);

					CreditCard sourceCard = DAO_FACTORY.getCreditCardDAO()
							.getById(sourceCardId);

					if (sourceCard != null) {

						sourceBankAccount = bankAccDAO
								.getById(sourceCard.getBankAccountId());
					}

					boolean isOutSideBankAccount = false;

					if (destinationAccount == null) {

						isOutSideBankAccount = true;

						destinationAccount = Maker.getInnerBankAccount(
								sourceBankAccount, bankAccDAO);
					}

					if (Validator.validateTransaction(sourceBankAccount,
							destinationAccount, sum)
							&& Validator.validateSum(sum)) {

						LocalDateTime dateTime = LocalDateTime.now();

						Transaction transaction = Maker.getTransaction(
								sourceBankAccount, destinationAccount,
								sourceCard, OperationType.PAY, sum, dateTime);

						if (isOutSideBankAccount) {

							transaction
									.setDestinationId(destinationBankAccountId);

							if (!OutputTransactionFileMaker
									.prepareOutSideTransaction(transaction)) {

								throw new ServiceLevelException(
										ConfigurationReader.getProperty(
												ConstantConteiner.SOME_PROBLEM_MSG));
							}

							transactionDAO.save(transaction);

							transaction.setDestinationId(
									destinationAccount.getAccountId());
						}

						transactionDAO.save(transaction);

						makeTransaction(sourceBankAccount, destinationAccount,
								sum);

					} else {

						throw new ServiceLevelException(
								ConfigurationReader.getProperty(
										ConstantConteiner.PAY_OPERATON_FAIL_MSG));
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

	@Override
	public void calculateCommision(HttpServletRequest request)
			throws ServiceLevelException {

		if (request != null) {

			CreditCard[] cardList;

			try {

				cardList = Maker.getCreditCardArray(request, cardDAO);

				double sum = getSum(request);

				if (cardList != null && Validator
						.validateCardReplenish(cardList[0], cardList[1])) {

					double transactionSum = Calculator
							.getTransactionSum(cardList[0], cardList[1], sum);

					double commision = Calculator.getCommision(transactionSum);

					request.setAttribute(
							ConfigurationReader.getProperty(
									ConstantConteiner.COMMISION_ATTREBUTE_NAME),
							commision);

					request.setAttribute(
							ConfigurationReader.getProperty(
									ConstantConteiner.TRANSACTION_SUM),
							transactionSum);

					request.setAttribute(
							ConfigurationReader.getProperty(
									ConstantConteiner.F_CREDIT_CARD_ID),
							cardList[0].getCreditCardId());

					request.setAttribute(ConfigurationReader.getProperty(
							ConstantConteiner.F_DESTINATION_CREDIT_CARD_ID),
							cardList[1].getCreditCardId());

					request.setAttribute(ConfigurationReader
							.getProperty(ConstantConteiner.SUM), sum);

					request.setAttribute(
							ConfigurationReader.getProperty(
									ConstantConteiner.IS_AFTER_CALCULATION),
							true);
				} else {
					throw new ServiceLevelException(
							ConfigurationReader.getProperty(
									ConstantConteiner.CANT_REPLENIASH_MSG));
				}

			} catch (DaoException e) {
				logger.error(
						"Try to get card in calculateCommision() methood in Creator class.");

				throw new ServiceLevelException(ConfigurationReader
						.getProperty(ConstantConteiner.SOME_PROBLEM_MSG));
			}

		} else {
			throw new ServiceLevelException(ConfigurationReader
					.getProperty(ConstantConteiner.INCORRECT_DATA_MSG));
		}
	}

	@Override
	public boolean makeReplenishOperation(HttpServletRequest request)
			throws ServiceLevelException {

		boolean result = false;

		if (request != null) {

			ArrayList<Operation> operatonList = new ArrayList<Operation>();
			ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

			try {

				CreditCard[] cardList = Maker.getCreditCardArray(request,
						cardDAO);

				double sum = getSum(request);

				double transactionSum = Calculator
						.getTransactionSum(cardList[0], cardList[1], sum);

				double commision = Calculator.getCommision(transactionSum);

				BankAccount innerBankAccountBYN = Maker
						.getInnerBankAccount(null, bankAccDAO);

				BankAccount sourceBankAccount = bankAccDAO
						.getById(cardList[0].getBankAccountId());

				BankAccount destinationBankAccount = bankAccDAO
						.getById(cardList[1].getBankAccountId());

				if (Validator.validateTransaction(sourceBankAccount, null,
						transactionSum)) {

					Operation commisionOperation = new Operation(
							sourceBankAccount, innerBankAccountBYN, commision);

					Operation sourceToBankOperation = new Operation(
							sourceBankAccount, innerBankAccountBYN,
							transactionSum);

					Operation bankToDestinationOperation = new Operation(
							innerBankAccountBYN, destinationBankAccount, sum);

					makeOperationBYN(commisionOperation);
					operatonList.add(commisionOperation);

					makeOperationBYN(sourceToBankOperation);
					operatonList.add(sourceToBankOperation);

					makeOperationBYN(bankToDestinationOperation);
					operatonList.add(bankToDestinationOperation);

					LocalDateTime dateTime = LocalDateTime.now();

					Transaction commisionTransaction = Maker.getTransactionBYN(
							sourceBankAccount, innerBankAccountBYN, cardList[0],
							OperationType.COMMISION, commision, dateTime);
					transactionDAO.save(commisionTransaction);
					transactionList.add(commisionTransaction);

					Transaction sourceToBankTransaction = Maker
							.getTransactionBYN(sourceBankAccount,
									innerBankAccountBYN, cardList[0],
									OperationType.REPLENISHMENT, transactionSum,
									dateTime);
					transactionDAO.save(sourceToBankTransaction);
					transactionList.add(sourceToBankTransaction);

					Transaction bankToDestinationTransaktion = Maker
							.getTransactionBYN(innerBankAccountBYN,
									destinationBankAccount, null,
									OperationType.REPLENISHMENT, sum, dateTime);
					transactionDAO.save(bankToDestinationTransaktion);
					transactionList.add(bankToDestinationTransaktion);

					Transaction sourceToDestinationTransaction = Maker
							.getTransaction(sourceBankAccount,
									destinationBankAccount, cardList[0],
									OperationType.REPLENISHMENT, transactionSum,
									dateTime);
					transactionDAO.save(sourceToDestinationTransaction);
					transactionList.add(sourceToDestinationTransaction);
				}

			} catch (DaoException e) {

				rollbackOperationList(operatonList);
				roolbackTransactionList(transactionList);

				logger.error(
						"Exception in makeReplenishOperation() methood in TransactionServiceImpl class");

				throw new ServiceLevelException(ConfigurationReader
						.getProperty(ConstantConteiner.SOME_PROBLEM_MSG));
			}
		}

		return result;
	}

	public void loadHistory(HttpServletRequest request)
			throws ServiceLevelException {
		if (request != null) {

			String bankAccountIdTmp = request.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_BANK_ACCOUNT_ID));

			long bankAccountId = 0;

			if (bankAccountIdTmp != null) {
				bankAccountId = Long.parseLong(bankAccountIdTmp);
			}

			try {

				request.getSession().setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.F_BANK_ACCOUNT_ID),
						bankAccountId);

				request.getSession().setAttribute(
						ConfigurationReader.getProperty(
								ConstantConteiner.TRANSACTION_LIST),
						transactionDAO.getAll(bankAccountId));

			} catch (DaoException e) {
				logger.error(
						"Can't get list of transaction in loadHistory() TransactionServiceImpl calss.\n"
								+ e);

				throw new ServiceLevelException(ConfigurationReader
						.getProperty(ConstantConteiner.CANT_LOAD_HISTORY_MSG));
			}

		}
	}

	private void makeTransaction(BankAccount sourceBankAccount,
			BankAccount destinationAccount, double sum) throws DaoException {

		sourceBankAccount
				.setAccountMoney(sourceBankAccount.getAccountMoney() - sum);

		destinationAccount
				.setAccountMoney(destinationAccount.getAccountMoney() + sum);

		bankAccDAO.update(sourceBankAccount);
		bankAccDAO.update(destinationAccount);
	}

	private void makeOperationBYN(Operation operation) throws DaoException {

		BankAccount innerBankAccount = Maker.getInnerBankAccount(null,
				bankAccDAO);

		double salesCourse = ExchangeCourseManager.getCourse(
				operation.destinationBankAccount.getCurrencyType(),
				ConstantConteiner.SALE_BYN_CONST);

		double buyCourse = ExchangeCourseManager.getCourse(
				operation.sourceBankAccount.getCurrencyType(),
				ConstantConteiner.BUY_BYN_CONST);

		boolean isInnerBankAccountSource = false;

		if (operation.sourceBankAccount != null
				&& operation.destinationBankAccount != null
				&& innerBankAccount != null && salesCourse > 0
				&& buyCourse > 0) {

			if (operation.sourceBankAccount.getAccountId() == innerBankAccount
					.getAccountId()) {

				operation.sourceBankAccount
						.setAccountMoney(Calculator.roundTwoDecimalPlace(
								operation.sourceBankAccount.getAccountMoney()
										- operation.sum * salesCourse));

				operation.destinationBankAccount.setAccountMoney(Calculator
						.roundTwoDecimalPlace(operation.destinationBankAccount
								.getAccountMoney() + operation.sum));

				isInnerBankAccountSource = true;

			} else {

				operation.sourceBankAccount
						.setAccountMoney(Calculator.roundTwoDecimalPlace(
								operation.sourceBankAccount.getAccountMoney()
										- operation.sum));

				operation.destinationBankAccount.setAccountMoney(Calculator
						.roundTwoDecimalPlace(operation.destinationBankAccount
								.getAccountMoney()
								+ operation.sum * buyCourse));

			}

			bankAccDAO.update(operation.sourceBankAccount);

			try {

				bankAccDAO.update(operation.destinationBankAccount);

			} catch (DaoException e) {

				if (isInnerBankAccountSource) {

					operation.sourceBankAccount.setAccountMoney(Calculator
							.roundTwoDecimalPlace(operation.sourceBankAccount
									.getAccountMoney()
									+ operation.sum * salesCourse));

					bankAccDAO.update(operation.sourceBankAccount);

				} else {

					operation.sourceBankAccount.setAccountMoney(Calculator
							.roundTwoDecimalPlace(operation.sourceBankAccount
									.getAccountMoney() + operation.sum));

					bankAccDAO.update(operation.sourceBankAccount);
				}

				throw new DaoException();
			}
		}
	}

	private void rollbackOperationList(List<Operation> list) {

		if (list != null) {
			for (Operation operation : list) {
				rollbackOperation(operation);
			}
		}
	}

	private void rollbackOperation(Operation operation) {

		BankAccount innerBankAccount;

		try {

			innerBankAccount = Maker.getInnerBankAccount(null, bankAccDAO);

			double salesCourse = ExchangeCourseManager.getCourse(
					operation.destinationBankAccount.getCurrencyType(),
					ConstantConteiner.SALE_BYN_CONST);

			double buyCourse = ExchangeCourseManager.getCourse(
					operation.sourceBankAccount.getCurrencyType(),
					ConstantConteiner.BUY_BYN_CONST);

			if (operation != null) {
				if (operation.sourceBankAccount != null
						&& operation.destinationBankAccount != null
						&& innerBankAccount != null && salesCourse > 0
						&& buyCourse > 0) {

					if (operation.sourceBankAccount
							.getAccountId() == innerBankAccount
									.getAccountId()) {

						operation.sourceBankAccount.setAccountMoney(
								Calculator.roundTwoDecimalPlace(
										operation.sourceBankAccount
												.getAccountMoney()
												+ operation.sum * salesCourse));

						operation.destinationBankAccount.setAccountMoney(
								Calculator.roundTwoDecimalPlace(
										operation.destinationBankAccount
												.getAccountMoney()
												- operation.sum));
					} else {

						operation.sourceBankAccount.setAccountMoney(
								Calculator.roundTwoDecimalPlace(
										operation.sourceBankAccount
												.getAccountMoney()
												+ operation.sum));

						operation.destinationBankAccount.setAccountMoney(
								Calculator.roundTwoDecimalPlace(
										operation.destinationBankAccount
												.getAccountMoney()
												- operation.sum * buyCourse));
					}

					try {
						bankAccDAO.update(operation.sourceBankAccount);
					} catch (DaoException e) {
						logger.fatal(
								"Can't rollback operation with sourceBankAccount:\n "
										+ operation);
					}

					try {
						bankAccDAO.update(operation.destinationBankAccount);
					} catch (DaoException e) {
						logger.fatal(
								"Can't rollback operation with destinationBankAccount:\n "
										+ operation);
					}
				}
			}
		} catch (DaoException e1) {
			logger.fatal("Can't rollback operation:\n " + operation);
		}
	}

	private void roolbackTransactionList(List<Transaction> list) {

		if (list != null) {
			for (Transaction transaction : list) {
				try {
					transactionDAO.delete(transaction);
				} catch (DaoException e) {
					logger.fatal(
							"Can't rollback transaction:\n " + transaction);
				}
			}
		}

	}

	private double getSum(HttpServletRequest request) {

		double result = 0;

		if (request != null) {

			String sumTmp = request.getParameter(
					ConfigurationReader.getProperty(ConstantConteiner.SUM));

			if (sumTmp != null && !sumTmp.isEmpty()) {

				result = Double.parseDouble(sumTmp);
			}
		}

		return result;
	}

	private class Operation {

		public BankAccount sourceBankAccount;
		public BankAccount destinationBankAccount;
		public double sum;

		public Operation(BankAccount sourceBankAccount,
				BankAccount destinationBankAccount, double sum) {

			this.sourceBankAccount = sourceBankAccount;
			this.destinationBankAccount = destinationBankAccount;
			this.sum = sum;
		}

		@Override
		public String toString() {
			return "Operation [sourceBankAccount=" + sourceBankAccount
					+ ",\n destinationBankAccount=" + destinationBankAccount
					+ ",\n sum=" + sum + "]";
		}

	}
}