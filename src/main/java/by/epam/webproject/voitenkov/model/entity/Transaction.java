package by.epam.webproject.voitenkov.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;
import by.epam.webproject.voitenkov.model.entity.enumeration.OperationType;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private long transactionId;
	private long resursId;
	private long destinationId;
	private OperationType operationType;
	private long cardId;
	private double sum;
	private CurrencyType currencyType;
	private LocalDateTime dateTime;


	public Transaction(long transactionId, long resursId, long destinationId,
			OperationType operationType, long cardId, double sum,
			CurrencyType currencyType, LocalDateTime dateTime) {
		super();
		this.transactionId = transactionId;
		this.resursId = resursId;
		this.destinationId = destinationId;
		this.operationType = operationType;
		this.cardId = cardId;
		this.sum = sum;
		this.currencyType = currencyType;
		this.dateTime = dateTime;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(float sum) {
		if (sum >= 0) {
			this.sum = sum;
		}
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		if (transactionId > 0) {
			this.transactionId = transactionId;
		}
	}

	public long getResursId() {
		return resursId;
	}

	public void setResursId(long resursId) {
		if (resursId > 0) {
			this.resursId = resursId;
		}
	}

	public long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(long destinationId) {
		if (destinationId > 0) {
			this.destinationId = destinationId;
		}
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		if (operationType != null) {
			this.operationType = operationType;
		}
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		if (cardId > 0) {
			this.cardId = cardId;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardId, currencyType, dateTime, destinationId,
				operationType, resursId, sum, transactionId);
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
		Transaction other = (Transaction) obj;
		return cardId == other.cardId && currencyType == other.currencyType
				&& Objects.equals(dateTime, other.dateTime)
				&& destinationId == other.destinationId
				&& operationType == other.operationType
				&& resursId == other.resursId
				&& Double.doubleToLongBits(sum) == Double
						.doubleToLongBits(other.sum)
				&& transactionId == other.transactionId;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", resursId="
				+ resursId + ", destinationId=" + destinationId
				+ ", operationType=" + operationType + ", cardId=" + cardId
				+ ", sum=" + sum + ", currencyType=" + currencyType
				+ ", dateTime=" + dateTime + "]";
	}
}
