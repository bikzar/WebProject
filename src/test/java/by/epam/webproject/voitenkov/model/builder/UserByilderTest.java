package by.epam.webproject.voitenkov.model.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import by.epam.webproject.voitenkov.dao.connectionpool.DBConnector;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementBankAccountBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementCreditCardBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementUserBuilder;
import by.epam.webproject.voitenkov.model.entity.User;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 20, 2019
 */
class UserByilderTest {

	@Test
	void buildTest() {

		DBConnector bd = DBConnector.getInstance();
		Connection con = bd.getConnection();

		try {
			PreparedStatement prepstat = con.prepareStatement(
					"SELECT bank_account.bank_account_id, bank_account.is_block as ba_block, acc_money, bank_account.user_id, "
							+ "credit_card_id, credit_card.is_block as cc_block, name, second_name, birth_date, is_admin, login, "
							+ "password, currency_abbreviation FROM bank_account "
							+ "LEFT JOIN credit_card ON bank_account.bank_account_id = credit_card.bank_account_id "
							+ "LEFT JOIN user ON bank_account.user_id = user.user_id "
							+ "left join currency_type on bank_account.currency_type = currency_type.currency_type_id "
							+ "WHERE bank_account.user_id = ?;");

			prepstat.setInt(1, 50);

			ResultSet res = prepstat.executeQuery();

			FromStatementCreditCardBuilder creditCardBuilder = new FromStatementCreditCardBuilder();

			FromStatementBankAccountBuilder accontBuilder = new FromStatementBankAccountBuilder(
					creditCardBuilder);

			User user = new FromStatementUserBuilder(accontBuilder)
					.build(res);

			assertEquals(ExpectedUser.getExpectedUser(), user);

		} catch (SQLException e) {
		}

	}

}
