package by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.FromStatemantBuilder;
import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 20, 2019
 */
public class FromStatementUserBuilder implements FromStatemantBuilder<User> {

	private FromStatemantBuilder<BankAccount> bankAccountBuilder;

	public FromStatementUserBuilder(
			FromStatemantBuilder<BankAccount> bankAccountBuilder) {
		this.bankAccountBuilder = bankAccountBuilder;
	}

	@Override
	public User build(ResultSet resultSet) throws SQLException {

		ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();
		User user = null;

		LocalDate birthDate = null;
		String secondName = null;
		boolean isAdmin = false;
		String password = null;
		String login = null;
		String name = null;
		long userId = 0;

		if (resultSet != null) {
			userId = resultSet.getLong(ConfigurationReader
					.getProperty(ConstantConteiner.DB_USER_ID));

			if (userId != 0) {

				name = resultSet.getString(ConfigurationReader
						.getProperty(ConstantConteiner.DB_USER_NAME));
				secondName = resultSet.getString(ConfigurationReader
						.getProperty(ConstantConteiner.DB_USER_SECOND_NAME));

				Date birthDateTemp = resultSet.getDate(ConfigurationReader
						.getProperty(ConstantConteiner.DB_BIRTH_DATE));

				birthDate = birthDateTemp.toLocalDate();

				isAdmin = resultSet.getBoolean(ConfigurationReader
						.getProperty(ConstantConteiner.DB_IS_ADMIN));

				login = resultSet.getString(ConfigurationReader
						.getProperty(ConstantConteiner.DB_LOGIN));

				password = resultSet.getString(ConfigurationReader
						.getProperty(ConstantConteiner.DB_USER_PASSWORD));
			}

			while (!resultSet.isAfterLast() && bankAccountBuilder != null) {

				BankAccount bankAccount = bankAccountBuilder.build(resultSet);

				if (bankAccount != null) {
					bankAccountList.add(bankAccount);
				}
			}
			
			resultSet.next();
			
			user = new User(userId, name, secondName, birthDate, isAdmin, login,
					password, bankAccountList);
		}

		return user;
	}
}