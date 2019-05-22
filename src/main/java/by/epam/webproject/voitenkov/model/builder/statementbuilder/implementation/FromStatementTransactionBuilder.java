package by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;
import by.epam.webproject.voitenkov.model.entity.enumeration.OperationType;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 22, 2019
 */
public class FromStatementTransactionBuilder
		implements FromStatemantBuilder<Transaction> {

	@Override
	public Transaction build(ResultSet resultSet) throws SQLException {

		Transaction result = null;

		if (resultSet != null) {

			long sourceId = resultSet.getLong(ConfigurationReader
					.getProperty(ConstantConteiner.DB_SOURCE_ID));

			long destinationId = resultSet.getLong(ConfigurationReader
					.getProperty(ConstantConteiner.DB_DESTINATION_ID));

			int operationTypeiInt = resultSet.getInt(ConfigurationReader
					.getProperty(ConstantConteiner.DB_OPERATION_TYPE));

			OperationType operetionType = OperationType
					.values()[operationTypeiInt-1];

			double operationSum = resultSet.getDouble(ConfigurationReader
					.getProperty(ConstantConteiner.DB_OPERATION_SUM));

			long cardId = resultSet.getLong(ConfigurationReader
					.getProperty(ConstantConteiner.DB_CREDIT_CARD_ID));

			int transactCurrencyTypeTmp = resultSet.getInt(ConfigurationReader
					.getProperty(ConstantConteiner.DB_TRANSACTION_CURRENCY));

			CurrencyType transactCurrencyType = CurrencyType
					.values()[transactCurrencyTypeTmp-1];

			String dateTimeTmp = resultSet.getString(ConfigurationReader
					.getProperty(ConstantConteiner.DB_TRANSACTION_DATE));

			String dateTimePattern = ConfigurationReader
					.getProperty(ConstantConteiner.DATE_TIME_PATTERN);

			LocalDateTime dateTime = LocalDateTime.parse(dateTimeTmp,
					DateTimeFormatter.ofPattern(dateTimePattern));

			result = new Transaction(0, sourceId, destinationId, operetionType,
					cardId, operationSum, transactCurrencyType, dateTime);
			
			resultSet.next();
		}

		return result;
	}

}
