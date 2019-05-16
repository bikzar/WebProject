package by.epam.webproject.voitenkov.dao.transaction;


import by.epam.webproject.voitenkov.model.entity.Transaction;

/**
 * @author Sergey Voitenkov
 *
 * May 10, 2019
 */
public interface TransactionDAO {
	
	/**
	 * @param transaction
	 * @return false - if transaction is null or some problem with connection.
	 * @throws CantCreateConnectinPoolException
	 */
	public boolean save(Transaction transaction);
	
}
