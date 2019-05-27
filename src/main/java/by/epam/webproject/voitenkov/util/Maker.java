package by.epam.webproject.voitenkov.util;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.model.dal.dao.AbstractDAO;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;
import by.epam.webproject.voitenkov.model.entity.enumeration.OperationType;
import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 20, 2019
 */
public class Maker {

	private static Logger logger = LogManager.getLogger();

	private Maker() {
	}

	/**
	 * @return null if some references type is null.
	 */
	public static Transaction getTransactionBYN(BankAccount sourceBankAccount,
			BankAccount destinationAccount, CreditCard sourceCard,
			OperationType operationType, double sum, LocalDateTime dateTime) {

		Transaction result = null;

		long id = 0;

		double salesCourse = ExchangeCourseManager.getCourse(
				destinationAccount.getCurrencyType(),
				ConstantConteiner.SALE_BYN_CONST);

		double buyCourse = ExchangeCourseManager.getCourse(
				sourceBankAccount.getCurrencyType(),
				ConstantConteiner.BUY_BYN_CONST);

		long innerBankBYNAccountId = Long.parseLong(ConfigurationReader
				.getProperty(ConstantConteiner.INNER_BYN_BANK_ACCOUNT));

		if (sourceBankAccount != null && destinationAccount != null
				&& operationType != null && dateTime != null) {

			if (sourceCard != null) {
				id = sourceCard.getCreditCardId();
			}

			if (sourceBankAccount.getAccountId() == innerBankBYNAccountId) {
				sum *= salesCourse;
			} else if (destinationAccount
					.getAccountId() == innerBankBYNAccountId) {
				sum *= buyCourse;
			}

			result = new Transaction(0, sourceBankAccount.getAccountId(),
					destinationAccount.getAccountId(), operationType, id,
					Calculator.roundTwoDecimalPlace(sum), CurrencyType.BYN,
					dateTime);
		}

		return result;
	}

	public static Transaction getTransaction(BankAccount sourceBankAccount,
			BankAccount destinationAccount, CreditCard sourceCard,
			OperationType operationType, double sum, LocalDateTime dateTime) {

		Transaction result = null;

		if (sourceCard != null && sourceBankAccount != null
				&& destinationAccount != null && operationType != null
				&& dateTime != null) {

			result = new Transaction(0, sourceBankAccount.getAccountId(),
					destinationAccount.getAccountId(), operationType,
					sourceCard.getCreditCardId(), sum,
					sourceBankAccount.getCurrencyType(), dateTime);
		}

		return result;
	}

	/**
	 * @param request
	 * @return CreditCard array where a[0] - source card; a[1] - destination
	 *         card; or null if request is null;
	 * @throws ServiceLevelException
	 * @throws DaoException
	 */
	public static CreditCard[] getCreditCardArray(HttpServletRequest request,
			AbstractDAO<CreditCard> cardDAO) throws DaoException {

		CreditCard[] cardList = null;

		if (request != null) {

			String destinationCardIdTmp = request
					.getParameter(ConfigurationReader.getProperty(
							ConstantConteiner.F_DESTINATION_CREDIT_CARD_ID));

			String sourceCardIdTmp = request.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_CREDIT_CARD_ID));

			if (destinationCardIdTmp != null && sourceCardIdTmp != null) {

				long destinationCardId = Long.parseLong(destinationCardIdTmp);

				long sourceCardId = Long.parseLong(sourceCardIdTmp);

				try {

					CreditCard sourceCard = cardDAO.getById(sourceCardId);
					CreditCard destinationCard = cardDAO
							.getById(destinationCardId);

					cardList = new CreditCard[] { sourceCard, destinationCard };

				} catch (DaoException e) {
					logger.error(
							"Try to get card in getCreditCardArray() methood in Creator class.");

					throw new DaoException();
				}
			}
		}

		return cardList;
	}

	/**
	 * @param sourceBankAccount
	 * @return Inner bank account in the same currency like source bankAccount.
	 *         If source bankAccount is null, will return inner bank's account
	 *         with currency type BYN.
	 * @throws DaoException
	 */
	public static BankAccount getInnerBankAccount(BankAccount sourceBankAccount,
			AbstractDAO<BankAccount> bankAccDAO) throws DaoException {

		BankAccount result = null;

		if (sourceBankAccount != null) {

			String innerBankAccountName = ConfigurationReader
					.getProperty(ConstantConteiner.PART_MAIN_BANK_ACCOUNT_NAME)
					+ sourceBankAccount.getCurrencyType();

			if (innerBankAccountName != null) {
				long id = Long.parseLong(
						ConfigurationReader.getProperty(innerBankAccountName));
				result = bankAccDAO.getById(id);
			}

		} else {

			String innerBankAccountName = ConfigurationReader.getProperty(
					ConstantConteiner.PART_MAIN_BANK_ACCOUNT_NAME) + "BYN";

			String idBYNTmp = ConfigurationReader
					.getProperty(innerBankAccountName);

			result = bankAccDAO.getById(Long.parseLong(idBYNTmp));
		}

		return result;
	}
}