package by.epam.webproject.voitenkov.dao.creditcard;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;

/**
 * @author Sergey Voitenkov
 *
 * May 14, 2019
 */
public interface CreditCardDAO {
	boolean updateIsBockColumnById(long id) throws DaoException;
}
