package by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 21, 2019
 */
public class FromStatementBankAccountBuilder
		implements FromStatemantBuilder<BankAccount> {

	private FromStatemantBuilder<CreditCard> creditCardBuilder;

	public FromStatementBankAccountBuilder(
			FromStatemantBuilder<CreditCard> creditCardBuilder) {
		this.creditCardBuilder = creditCardBuilder;
	}

	@Override
	public BankAccount build(ResultSet resultSet) throws SQLException {

		BankAccount result = null;

		long accountId = 0;
		long accountMoney = 0;
		CurrencyType currencyType = null;
		boolean isBlock = false;
		long userId = 0;
		ArrayList<CreditCard> creditCardList = new ArrayList<CreditCard>();

		if (resultSet != null) {

			accountId = resultSet.getLong(ConfigurationReader
					.getProperty(ConstantConteiner.DB_BANK_ACCOUNT_ID));

			if (accountId != 0) {

				accountMoney = resultSet.getLong(ConfigurationReader
						.getProperty(ConstantConteiner.DB_BANK_ACCOUNT_MONEY));

				String temp = resultSet
						.getString(ConfigurationReader.getProperty(
								ConstantConteiner.DB_BANK_ACCOUNT_CURR_ABR));
				currencyType = CurrencyType.valueOf(temp.toUpperCase());

				isBlock = resultSet.getBoolean(ConfigurationReader.getProperty(
						ConstantConteiner.DB_BANK_ACCOUNT_IS_BLOCK));

				userId = resultSet.getLong(ConfigurationReader
						.getProperty(ConstantConteiner.DB_USER_ID));

				/*
				 * if currentBankAccountId == accountId that mean that next
				 * (resultSet always next() in
				 * creditCardBuilder.build(resultSet)) card in resultSet should
				 * be add in current Bank Account
				 */
				long currentBankAccountId = accountId;

				while (accountId == currentBankAccountId
						&& !resultSet.isAfterLast()
						&& creditCardBuilder != null) {

					CreditCard card = creditCardBuilder.build(resultSet);

					if (card != null) {
						creditCardList.add(card);
					}

					if (!resultSet.isAfterLast()) {
						currentBankAccountId = resultSet
								.getLong(ConfigurationReader.getProperty(
										ConstantConteiner.DB_BANK_ACCOUNT_ID));
					}
				}

				result = new BankAccount(accountId, isBlock, accountMoney,
						currencyType, userId, creditCardList);
			}

			resultSet.next();
		}

		return result;
	}
}
