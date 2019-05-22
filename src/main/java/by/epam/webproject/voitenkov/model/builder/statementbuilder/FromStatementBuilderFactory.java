package by.epam.webproject.voitenkov.model.builder.statementbuilder;

import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementBankAccountBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementCreditCardBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementTransactionBuilder;
import by.epam.webproject.voitenkov.model.builder.statementbuilder.implementation.FromStatementUserBuilder;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 27, 2019
 */
public class FromStatementBuilderFactory {

	private FromStatementCreditCardBuilder creditCardBuilder = new FromStatementCreditCardBuilder();
	private FromStatementBankAccountBuilder bankAccBuilder = new FromStatementBankAccountBuilder(
			null);
	private FromStatementUserBuilder userBuilder = new FromStatementUserBuilder(
			null);
	private FromStatementTransactionBuilder transactionBuilder = new FromStatementTransactionBuilder();

	private FromStatementBuilderFactory() {
	}

	private static class InstanseBuilder {
		private static final FromStatementBuilderFactory INSTANCE = new FromStatementBuilderFactory();
	}

	public static FromStatementBuilderFactory getInstance() {
		return InstanseBuilder.INSTANCE;
	}

	public FromStatementCreditCardBuilder getCreditCardBuilder() {
		return creditCardBuilder;
	}

	public FromStatementBankAccountBuilder getBankAccBuilder() {
		return bankAccBuilder;
	}

	public FromStatementUserBuilder getUserBuilder() {
		return userBuilder;
	}

	public FromStatementTransactionBuilder getTransactionBuilder() {
		return transactionBuilder;
	}

}
