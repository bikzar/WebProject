package by.epam.webproject.voitenkov.dao.creditcard;

import java.util.ArrayList;
import java.util.List;

import by.epam.webproject.voitenkov.dao.AbstractDAO;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatementBuilderFactory;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         May 14, 2019
 */
public class CreditCardDAOImpl extends AbstractDAO<CreditCard>
		implements CreditCardDAO {

	private static FromStatementBuilderFactory factory;
	private static FromStatemantBuilder<CreditCard> builder;

	private CreditCardDAOImpl() {
		factory = FromStatementBuilderFactory.getInstance();
		builder = factory.getCreditCardBuilder();
	}

	private static class InstanceCreator {
		private static final CreditCardDAOImpl INSTANCE = new CreditCardDAOImpl();
	}

	public static CreditCardDAOImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public CreditCard getById(long id) throws DaoException {

		CreditCard card = null;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.GET_CREDIT_CARD_BY_ID);

		if (Validator.validateID(id)) {
			card = executeSingleResultQuery(query, builder, id);
		}

		return card;
	}

	@Override
	public List<CreditCard> getAll(long bankAccountId) throws DaoException {

		List<CreditCard> resultList = new ArrayList<CreditCard>();

		String query = ConfigurationReader.getProperty(
				ConstantConteiner.GET_ALL_CREDIT_CARD_BY_BANK_ACCOUNT_ID);

		if (Validator.validateID(bankAccountId)) {
			resultList = executeQueryList(query, builder, bankAccountId);
		}

		return resultList;
	}

	@Override
	public boolean save(CreditCard entity) throws DaoException {
		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.SAVE_CREDIT_CARD);

		if (entity != null) {
			Object[] entityElements = { entity.isBlock(),
					entity.getCyrrencyType().ordinal() + 1,
					entity.getBankAccountId() };

			result = executeUpdateQuery(query, entityElements);
		}

		return result;
	}

	@Override
	public boolean update(CreditCard entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.UPDATE_CREDIT_CARD);

		if (entity != null) {
			Object[] entityElements = { entity.isBlock(),
					entity.getCyrrencyType().ordinal() + 1,
					entity.getBankAccountId(), entity.getCreditCardId() };

			result = executeUpdateQuery(query, entityElements);
		}

		return result;
	}

	@Override
	public boolean delete(CreditCard entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.DELETE_CREDIT_CARD);

		if (entity != null) {
			result = executeUpdateQuery(query, entity.getCreditCardId());
		}

		return result;
	}

	@Override
	public boolean updateIsBockColumnById(long id) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.BLOCK_CREDIT_CARD_BY_ID);

		if (Validator.validateID(id)) {
			result = executeUpdateQuery(query, id);
		}

		return result;
	}
}