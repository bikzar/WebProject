package by.epam.webproject.voitenkov.util.validator;

import by.epam.webproject.voitenkov.model.entity.BankAccount;

/**
 * @author Sergey Voitenkov
 *
 *         May 13, 2019
 */
public class Validator {

	public static boolean validateID(Long element) {
		return element > 0;
	}

	public static boolean validateTransaction(BankAccount sourceBankAccount,
			BankAccount destinationBankAccount, double sum) {

		boolean result = false;

		if (sourceBankAccount != null && destinationBankAccount != null) {

			result = (sourceBankAccount.getAccountMoney() - sum) >= 0
					&& sourceBankAccount.getCurrencyType()
							.equals(destinationBankAccount.getCurrencyType());
		}

		return result;
	}

}
