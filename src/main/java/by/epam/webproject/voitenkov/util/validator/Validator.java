package by.epam.webproject.voitenkov.util.validator;

import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;

/**
 * @author Sergey Voitenkov
 *
 *         May 13, 2019
 */
public class Validator {

	public static boolean validateID(Long element) {
		return element > 0;
	}

	/**
	 * @param sourceBankAccount
	 * @param destinationBankAccount
	 * @param sum                    - transaction sum
	 * @return if destination bankAccount != null (return true if source money -
	 *         sum >= 0 and both account has the same currency type). If
	 *         destination bankAccount is null return true if source bank
	 *         account money - sum >= 0.
	 */
	public static boolean validateTransaction(BankAccount sourceBankAccount,
			BankAccount destinationBankAccount, double sum) {

		boolean result = false;

		if (sourceBankAccount != null && destinationBankAccount != null
				&& !sourceBankAccount.isBlock()
				&& !destinationBankAccount.isBlock()) {

			result = (sourceBankAccount.getAccountMoney() - sum) >= 0
					&& sourceBankAccount.getCurrencyType()
							.equals(destinationBankAccount.getCurrencyType());
		} else if (sourceBankAccount != null
				&& destinationBankAccount == null) {

			result = (sourceBankAccount.getAccountMoney() - sum) >= 0;

		}

		return result;
	}

	public static boolean validateCardReplenish(CreditCard source,
			CreditCard destination) {

		boolean result = false;

		if (source != null && destination != null) {
			result = !source.equals(destination) && source
					.getBankAccountId() != destination.getBankAccountId();
		}

		return result;
	}

	public static boolean validateSum(double sum) {
		return sum > 0;
	}

}
