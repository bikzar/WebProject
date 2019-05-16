package by.epam.webproject.voitenkov.model.entity;

import java.io.Serializable;

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

	public Transaction(long transactionId, long resursId, long destinationId,
			OperationType operationType, long cardId, double sum) {
		this.transactionId = transactionId;
		this.resursId = resursId;
		this.destinationId = destinationId;
		this.operationType = operationType;
		this.cardId = cardId;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cardId ^ (cardId >>> 32));
		result = prime * result
				+ (int) (destinationId ^ (destinationId >>> 32));
		result = prime * result
				+ ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + (int) (resursId ^ (resursId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ (int) (transactionId ^ (transactionId >>> 32));
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
		Transaction other = (Transaction) obj;
		if (cardId != other.cardId) {
			return false;
		}
		if (destinationId != other.destinationId) {
			return false;
		}
		if (operationType != other.operationType) {
			return false;
		}
		if (resursId != other.resursId) {
			return false;
		}
		if (Double.doubleToLongBits(sum) != Double
				.doubleToLongBits(other.sum)) {
			return false;
		}
		if (transactionId != other.transactionId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", resursId="
				+ resursId + ", destinationId=" + destinationId
				+ ", operationType=" + operationType + ", cardId=" + cardId
				+ "]";
	}
}
