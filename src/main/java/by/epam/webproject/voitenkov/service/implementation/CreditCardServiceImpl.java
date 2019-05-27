package by.epam.webproject.voitenkov.service.implementation;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.DAOFactory;
import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.creditcard.CreditCardDAOImpl;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.service.CreditCardService;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         May 14, 2019
 */
public class CreditCardServiceImpl implements CreditCardService {

	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();

	private static CreditCardDAOImpl cardDAO = DAO_FACTORY.getCreditCardDAO();
	private static Logger logger = LogManager.getLogger();

	private CreditCardServiceImpl() {
	}

	private static class InstanceCreator {
		private static final CreditCardServiceImpl INSTANCE = new CreditCardServiceImpl();
	}

	public static CreditCardServiceImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public boolean lockCreditCard(HttpServletRequest request)
			throws ServiceLevelException {

		boolean result = false;

		try {

			result = updateCardStatus(request, true);

		} catch (DaoException e) {

			throw new ServiceLevelException(ConfigurationReader
					.getProperty(ConstantConteiner.CANT_LOCK_MSG));
		}

		return result;
	}

	@Override
	public boolean unLockCreditCard(HttpServletRequest request)
			throws ServiceLevelException {
		boolean result = false;

		try {

			result = updateCardStatus(request, false);

		} catch (DaoException e) {
			throw new ServiceLevelException(ConfigurationReader
					.getProperty(ConstantConteiner.CANT_UNLOCK_MSG));
		}

		return result;
	}

	@Override
	public boolean addCard(HttpServletRequest req)
			throws ServiceLevelException {
		boolean result = false;

		if (req != null) {
			String idTmp = req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_BANK_ACCOUNT_ID));

			if (idTmp != null && !idTmp.isEmpty()) {

				long id = Long.valueOf(idTmp);

				BankAccountDAOImpl bankAccDao = DAO_FACTORY.getBankAccountDAO();

				try {

					BankAccount bankAccount = bankAccDao.getById(id);

					if (bankAccDao != null) {

						CreditCard card = new CreditCard(0, false,
								bankAccount.getCurrencyType(),
								bankAccount.getAccountId());

						cardDAO.save(card);

						putCardListToBankAccList(req, id);
					}

				} catch (DaoException e) {
					throw new ServiceLevelException(ConfigurationReader
							.getProperty(ConstantConteiner.CANT_ADD_CARD_MSG));
				}
			}
		}

		return result;
	}

	@Override
	public boolean deleteCrad(HttpServletRequest req)
			throws ServiceLevelException {
		boolean result = false;

		if (req != null) {

			String idTmp = req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_CREDIT_CARD_ID));

			if (idTmp != null) {

				long id = Long.parseLong(idTmp);

				if (Validator.validateID(id)) {

					try {

						CreditCard card = cardDAO.getById(id);

						if (card != null) {

							long accountId = card.getBankAccountId();

							cardDAO.delete(card);

							putCardListToBankAccList(req, accountId);
						}

					} catch (DaoException e) {
						logger.error(
								"Can't delete card deleteCrad() methood CreditCardServiceImpl calss");
						throw new ServiceLevelException(
								ConfigurationReader.getProperty(
										ConstantConteiner.CANT_DELETE_MSG));
					}
				}
			}
		}

		return result;
	}

	private boolean updateCardStatus(HttpServletRequest req, boolean isLock)
			throws DaoException {

		boolean result = false;

		if (req != null) {

			String tempId = req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_CREDIT_CARD_ID));

			if (tempId != null && !tempId.isEmpty()) {

				long id = Integer.parseInt(tempId);

				if (Validator.validateID(id)) {

					try {

						result = cardDAO.updateIsBockColumnById(id, isLock);

						if (result) {

							CreditCard card = cardDAO.getById(id);

							if (card != null) {

								List<CreditCard> cardList = putCardListToBankAccList(
										req, card.getBankAccountId());

								req.getSession().setAttribute(
										ConfigurationReader.getProperty(
												ConstantConteiner.CREDIT_CARD_LIST),
										cardList);
							}
						}
					} catch (DaoException e) {
						logger.warn(
								"blockCreditCard() methood in CreditCardService class.");

						throw e;
					}
				}
			}
		}

		return result;
	}

	private List<CreditCard> putCardListToBankAccList(HttpServletRequest req,
			long bankAccountId) throws DaoException {

		List<CreditCard> cardList = cardDAO.getAll(bankAccountId);

		Object accList = req.getSession().getAttribute(ConfigurationReader
				.getProperty(ConstantConteiner.BANK_ACCOUNT_LIST));

		if (accList != null && accList instanceof List) {

			for (BankAccount acc : (List<BankAccount>) accList) {

				if (acc.getAccountId() == bankAccountId) {

					acc.setCreditCardList(cardList);
				}
			}
		}

		return cardList;
	}
}
