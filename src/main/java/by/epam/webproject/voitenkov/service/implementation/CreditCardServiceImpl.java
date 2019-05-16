package by.epam.webproject.voitenkov.service.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.dao.DAOFactory;
import by.epam.webproject.voitenkov.dao.creditcard.CreditCardDAOImpl;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.builder.formbuilder.FromFormBuilder;
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

	private FromFormBuilder<CreditCard> builder; // ????

	private CreditCardServiceImpl() {
	}

	private static class InstanceCreator {
		private static final CreditCardServiceImpl INSTANCE = new CreditCardServiceImpl();
	}

	public static CreditCardServiceImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public boolean blockCreditCard(HttpServletRequest request)
			throws ServiceLevelException {

		String tempId = request.getParameter(ConfigurationReader
				.getProperty(ConstantConteiner.F_CREDIT_CARD_ID));

		boolean result = false;

		if (tempId != null && request != null) {

			long id = Integer.parseInt(tempId);

			if (Validator.validateID(id)) {

				try {

					result = cardDAO.updateIsBockColumnById(id);

					if (result) {

						CreditCard card = cardDAO.getById(id);

						if (card != null) {

							List<CreditCard> cardList = cardDAO
									.getAll(card.getBankAccountId());

							request.getSession().setAttribute(
									ConfigurationReader.getProperty(
											ConstantConteiner.CREDIT_CARD_LIST),
									cardList);
						}
					}
				} catch (DaoException e) {
					logger.warn(
							" blockCreditCard() methood in CreditCardService class.");

					throw new ServiceLevelException(
							"Sorry can't block this card");
				}
			}
		}

		return result;
	}
}
