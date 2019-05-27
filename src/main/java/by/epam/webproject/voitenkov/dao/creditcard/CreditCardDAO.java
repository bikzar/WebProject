package by.epam.webproject.voitenkov.dao.creditcard;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;

/**
 * @author Sergey Voitenkov
 *
 * May 14, 2019
 */
public interface CreditCardDAO {
	
	
	/**
	 * @param id - id card number
	 * @param isLock - true for lock, false for unlock card.
	 * @return
	 * @throws DaoException
	 */
	boolean updateIsBockColumnById(long id, boolean isLock) throws DaoException;
}
