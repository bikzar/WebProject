package by.epam.webproject.voitenkov.model.builder.formbuilder.implementation;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.model.builder.formbuilder.FromFormBuilder;
import by.epam.webproject.voitenkov.model.entity.Transaction;

/**
 * @author Sergey Voitenkov
 *
 *         May 11, 2019
 */
public class TransactionFromFormBuilder
		implements FromFormBuilder<Transaction> {

	private TransactionFromFormBuilder() {
	}

	private static class InstanceCreator {
		private static final TransactionFromFormBuilder INSTANCE = new TransactionFromFormBuilder();
	}
	
	public static TransactionFromFormBuilder getInstance() {
		return InstanceCreator.INSTANCE;
	}

	@Override
	public Transaction build(HttpServletRequest req) {
		
				
		return null;
	}

}
