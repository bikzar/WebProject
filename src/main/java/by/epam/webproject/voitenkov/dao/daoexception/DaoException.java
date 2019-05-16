package by.epam.webproject.voitenkov.dao.daoexception;

import by.epam.webproject.voitenkov.appexception.TecnicalPaymentSystemException;

/**
 * @author Sergey Voitenkov
 *
 * Apr 28, 2019
 */
public class DaoException extends TecnicalPaymentSystemException {

	public DaoException() {
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
