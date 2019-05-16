package by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 20, 2019
 */
public class FromStatementCreditCardBuilder
		implements FromStatemantBuilder<CreditCard> {

	@Override
	public CreditCard build(ResultSet resultSet) throws SQLException {

		CreditCard result = null;

		int creditCardId = 0;
		boolean isBlock = true;
		CurrencyType currencyType = null;
		long bankAccountId = 0;

		if (resultSet != null) {

			creditCardId = resultSet.getInt(ConfigurationReader
					.getProperty(ConstantConteiner.DB_CREDIT_CARD_ID));

			if (creditCardId > 0) {

				isBlock = resultSet.getBoolean(ConfigurationReader.getProperty(
						ConstantConteiner.DB_CREDIT_CARD_IS_BLOCK));

				String temp = resultSet
						.getString(ConfigurationReader.getProperty(
								ConstantConteiner.DB_BANK_ACCOUNT_CURR_ABR));

				currencyType = CurrencyType.valueOf(temp.toUpperCase());

				bankAccountId = resultSet.getLong(ConfigurationReader
						.getProperty(ConstantConteiner.DB_BANK_ACCOUNT_ID));

				result = new CreditCard(creditCardId, isBlock, currencyType,
						bankAccountId);
			}
			
			resultSet.next();
		}

		return result;
	}
}
