package by.epam.webproject.voitenkov.model.entity;

import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */
public class CreditCard {

	private long creditCardId;
	private boolean isBlock;
	private CurrencyType currencyType;
	private long bankAccountId;

	{
		currencyType = CurrencyType.BYN;
	}

	public CreditCard() {
	}

	public CreditCard(long creditCardId, boolean isBlock,
			CurrencyType cyrrencyType, long bankAccountId) {

		setCreditCardId(creditCardId);
		this.isBlock = isBlock;
		setCyrrencyType(cyrrencyType);
		setBankAccountId(bankAccountId);
	}

	public CurrencyType getCyrrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType - should be bigger than 0;
	 */
	public void setCyrrencyType(CurrencyType currencyType) {
		if (currencyType != null) {
			this.currencyType = currencyType;
		}
	}

	public long getCreditCardId() {
		return creditCardId;
	}

	/**
	 * @param creditCardId - should be bigger than 0;
	 */
	public void setCreditCardId(long creditCardId) {
		if (creditCardId > 0) {
			this.creditCardId = creditCardId;
		}
	}

	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	/**
	 * @param bankAccountId - should be bigger than 0;
	 */
	public void setBankAccountId(long bankAccountId) {
		if (bankAccountId > 0) {
			this.bankAccountId = bankAccountId;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (bankAccountId ^ (bankAccountId >>> 32));
		result = prime * result + (int) (creditCardId ^ (creditCardId >>> 32));
		result = prime * result
				+ ((currencyType == null) ? 0 : currencyType.hashCode());
		result = prime * result + (isBlock ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CreditCard other = (CreditCard) obj;
		if (bankAccountId != other.bankAccountId) {
			return false;
		}
		if (creditCardId != other.creditCardId) {
			return false;
		}
		if (currencyType != other.currencyType) {
			return false;
		}
		if (isBlock != other.isBlock) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CreditCard [creditCardId=" + creditCardId + ", isBlock="
				+ isBlock + ", cyrrencyType=" + currencyType
				+ ", bankAccountId=" + bankAccountId + "]";
	}
}
