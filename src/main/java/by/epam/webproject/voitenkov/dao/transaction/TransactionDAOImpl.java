package by.epam.webproject.voitenkov.dao.transaction;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import by.epam.webproject.voitenkov.dao.AbstractDAO;
import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatementBuilderFactory;
import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;
import by.epam.webproject.voitenkov.util.validator.Validator;

/**
 * @author Sergey Voitenkov
 *
 *         May 10, 2019
 */
public class TransactionDAOImpl extends AbstractDAO<Transaction>
		implements TransactionDAO {

	private static FromStatementBuilderFactory factory;
	private static FromStatemantBuilder<Transaction> builder;

	private TransactionDAOImpl() {
		factory = FromStatementBuilderFactory.getInstance();
		builder = factory.getTransactionBuilder();
	}
	
	private static class InstanceCreator{
		private static final TransactionDAOImpl INSTANCE = new TransactionDAOImpl();
	}
	
	public static TransactionDAOImpl getInstance(){
		return InstanceCreator.INSTANCE;
	}

	@Override
	public boolean save(Transaction transaction) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.SAVE_TRANSACTION);

		if (transaction != null) {

			String dateTimePattern = ConfigurationReader
					.getProperty("DateTimePattern");
			String dateTime = transaction.getDateTime()
					.format(DateTimeFormatter.ofPattern(dateTimePattern));

			Object[] transactionElements = { transaction.getResursId(),
					transaction.getDestinationId(),
					transaction.getOperationType().ordinal() + 1,
					transaction.getCardId(), transaction.getSum(),
					transaction.getCurrencyType().ordinal() + 1, dateTime };

			result = executeUpdateQuery(query, transactionElements);
		}

		return result;
	}

	@Override
	public Transaction getById(long id) throws DaoException {
		return null;
	}

	@Override
	public List<Transaction> getAll(long id) throws DaoException {

		List<Transaction> list = null;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.LOAD_HISTORY_QUERY);

		if (Validator.validateID(id)) {
			list = executeQueryList(query, builder, id);
		}

		return list;
	}

	@Override
	public boolean update(Transaction entity) throws DaoException {
		return false;
	}

	@Override
	public boolean delete(Transaction transaction) throws DaoException {

		boolean result = false;

		String query = ConfigurationReader
				.getProperty(ConstantConteiner.DELETE_TRANSACTION);

		if (transaction != null) {

			String dateTimePattern = ConfigurationReader
					.getProperty("DateTimePattern");
			String dateTime = transaction.getDateTime()
					.format(DateTimeFormatter.ofPattern(dateTimePattern));
			// resurs_id destination_id operation_type credit_card_id
			// operation_sum trasaction_currency_type date
			Object[] transactionElements = { transaction.getResursId(),
					transaction.getDestinationId(),
					transaction.getOperationType().ordinal() + 1,
					transaction.getCardId(), transaction.getSum(),
					transaction.getCurrencyType().ordinal() + 1, dateTime };

			result = executeUpdateQuery(query, transactionElements);
		}

		return result;
	}

}