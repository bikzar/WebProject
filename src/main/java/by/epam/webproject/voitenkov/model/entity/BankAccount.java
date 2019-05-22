package by.epam.webproject.voitenkov.model.entity;

import java.util.List;

import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */
public class BankAccount {
	
	private long accountId;
	private boolean isBlock;
	private double accountMoney;
	private CurrencyType currencyType;
	private long userId;
	private List<CreditCard> creditCardList;

	public BankAccount() {
	}

	public BankAccount(long accountId, boolean isBlock, double accountMoney,
			CurrencyType currencyType, long userId,
			List<CreditCard> creditCardList) {
		this.accountId = accountId;
		this.isBlock = isBlock;
		this.accountMoney = accountMoney;
		this.currencyType = currencyType;
		this.userId = userId;
		this.creditCardList = creditCardList;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		if (currencyType != null) {
			this.currencyType = currencyType;
		}
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		if (accountId > 0) {
			this.accountId = accountId;
		}
	}

	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public double getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(double accountMoney) {
		if (accountMoney >= 0) {
			this.accountMoney = accountMoney;
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		if (userId > 0) {
			this.userId = userId;
		}
	}

	public List<CreditCard> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(List<CreditCard> creditCardList) {
		if (creditCardList != null) {
			this.creditCardList = creditCardList;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(accountMoney);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((creditCardList == null) ? 0 : creditCardList.hashCode());
		result = prime * result
				+ ((currencyType == null) ? 0 : currencyType.hashCode());
		result = prime * result + (isBlock ? 1231 : 1237);
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		BankAccount other = (BankAccount) obj;
		if (accountId != other.accountId) {
			return false;
		}
		if (Double.doubleToLongBits(accountMoney) != Double
				.doubleToLongBits(other.accountMoney)) {
			return false;
		}
		if (creditCardList == null) {
			if (other.creditCardList != null) {
				return false;
			}
		} else if (!creditCardList.equals(other.creditCardList)) {
			return false;
		}
		if (currencyType != other.currencyType) {
			return false;
		}
		if (isBlock != other.isBlock) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", isBlock=" + isBlock
				+ ", accountMoney=" + accountMoney + ", currencyType="
				+ currencyType + ", userId=" + userId + ", creditCardList="
				+ creditCardList + "]";
	}
}
