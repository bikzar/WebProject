package by.epam.webproject.voitenkov.service.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.DAOFactory;
import by.epam.webproject.voitenkov.dao.bankaccount.BankAccountDAOImpl;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.builder.formbuilder.FromFormBuilder;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.service.BankAccountService;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public class BankAccountServiceImpl implements BankAccountService {

	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private static BankAccountDAOImpl accountDAO = DAO_FACTORY
			.getBankAccountDAO();
	private static Logger logger = LogManager.getLogger();

	private FromFormBuilder<BankAccount> builder; // ????

	private BankAccountServiceImpl() {
	}

	private static class InstanceCreator {
		private static final BankAccountServiceImpl INSTANCE = new BankAccountServiceImpl();
	}

	public static BankAccountServiceImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public void loadBankAccount(HttpServletRequest req)
			throws ServiceLevelException {

		if (req != null) {

			String stringId = req.getParameter(ConfigurationReader
					.getProperty(ConstantConteiner.F_BANK_ACCOUNT_ID));

			if (stringId != null) {

				int id = Integer.parseInt(stringId);

				try {

					BankAccount bankAccount = accountDAO.getById(id);

					req.getSession().setAttribute(
							ConfigurationReader.getProperty(
									ConstantConteiner.BANK_ACCOUNT),
							bankAccount);

					List<CreditCard> cardList = DAO_FACTORY.getCreditCardDAO()
							.getAll(bankAccount.getAccountId());

					req.getSession().setAttribute(
							ConfigurationReader.getProperty(
									ConstantConteiner.CREDIT_CARD_LIST),
							cardList);

				} catch (DaoException e) {
					logger.warn(
							"Try to add attribute in loadBankAccount() methood  BankAccountServiceImpl class");
					throw new ServiceLevelException(e);
				}
			}
		}
	}

	public void loadPayFrom(HttpServletRequest req)
			throws ServiceLevelException {

		if (req != null) {
			User user = (User) req.getSession().getAttribute(
					ConfigurationReader.getProperty(ConstantConteiner.USER));

			if (user != null) {
				try {
					List<BankAccount> accountList = accountDAO
							.getAll(user.getUserId());

					if (!accountList.isEmpty()) {
						for (BankAccount account : accountList) {
							if (account != null) {

								account.setCreditCardList(
										DAO_FACTORY.getCreditCardDAO().getAll(
												account.getAccountId()));
							}
						}
					}

					req.getSession()
							.setAttribute(ConfigurationReader.getProperty(
									ConstantConteiner.BANK_ACCOUNT_LIST),
									accountList);

				} catch (DaoException e) {
					logger.error(
							"Try to load bankAccounList in loadPayFrom() methood in BankAccountServiceImpl calss.");
					throw new ServiceLevelException(
							ConfigurationReader.getProperty(
									ConstantConteiner.CANT_LOAD_PAY_FORM_MSG));
				}
			}
		}
	}
}