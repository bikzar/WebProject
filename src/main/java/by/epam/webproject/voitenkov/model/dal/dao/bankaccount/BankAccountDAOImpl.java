package by.epam.webproject.voitenkov.model.dal.dao.bankaccount;

import java.util.ArrayList;
import java.util.List;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatementBuilderFactory;
import by.epam.webproject.voitenkov.model.dal.dao.AbstractDAO;
import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public class BankAccountDAOImpl extends AbstractDAO<BankAccount>
		implements BankAccountDAO {

	FromStatemantBuilder<BankAccount> builder = FromStatementBuilderFactory
			.getInstance().getBankAccBuilder();

	private BankAccountDAOImpl() {
	}

	private static class InstanceCreator {
		private static final BankAccountDAOImpl INSTANCE = new BankAccountDAOImpl();
	}

	public static BankAccountDAOImpl getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public BankAccount getById(long id) throws DaoException {

		BankAccount account = null;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.GET_BANK_ACCOUNT_BY_ID);

		if (Validator.validateID(id)) {
			account = executeSingleResultQuery(query, builder, id);
		}

		return account;
	}

	@Override
	public List<BankAccount> getAll(long userId) throws DaoException {

		List<BankAccount> accounts = null;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.GET_ALL_BANK_ACCOUNT_BY_ID);

		if (Validator.validateID(userId)) {
			accounts = new ArrayList<BankAccount>();
			accounts = executeQueryList(query, builder, userId);
		}

		return accounts;
	}

	@Override
	public boolean save(BankAccount entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.SAVE_BANK_ACCOUNT);

		if (entity != null) {

			Object[] bankAccountElements = { entity.isBlock(),
					entity.getAccountMoney(),
					entity.getCurrencyType().ordinal() + 1,
					entity.getUserId() };

			result = executeUpdateQuery(query, bankAccountElements);
		}

		return result;
	}

	@Override
	public boolean update(BankAccount entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.UPDATE_BANK_ACCOUNT);

		if (entity != null) {

			Object[] bankAccountElements = { entity.isBlock(),
					entity.getAccountMoney(),
					entity.getCurrencyType().ordinal() + 1, entity.getUserId(),
					entity.getAccountId() };

			result = executeUpdateQuery(query, bankAccountElements);
		}

		return result;
	}

	@Override
	public boolean delete(BankAccount entity) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.DELETE_BANK_ACCOUNT_BY_ID);

		if (entity != null) {
			Object[] bankAccountElements = { entity.getAccountId() };

			result = executeUpdateQuery(query, bankAccountElements);
		}

		return result;
	}

	@Override
	public boolean deleteById(long bankAccountId) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.DELETE_BANK_ACCOUNT_BY_ID);

		if (Validator.validateID(bankAccountId)) {

			Object[] bankAccountElements = { bankAccountId };

			result = executeUpdateQuery(query, bankAccountElements);
		}

		return result;
	}

	@Override
	public boolean updateIsBlockColumnById(long id, boolean isLock) throws DaoException {
		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.UNLOCK_BANK_ACCOUNT_QUERY);

		if (isLock) {
			query = ConfigurationReader
					.getProperty(ConstantConteiner.LOCK_BANK_ACCOUNT_QUERY);
		}
		
		if(Validator.validateID(id)) {
			
			result = executeUpdateQuery(query, id);
		}

		return result;
	}
}
