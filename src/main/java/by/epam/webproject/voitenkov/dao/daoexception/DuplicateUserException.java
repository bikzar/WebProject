package by.epam.webproject.voitenkov.dao.daoexception;

import by.epam.webproject.voitenkov.appexception.TecnicalPaymentSystemException;

/**
 * @author Sergey Voitenkov
 *
 * Apr 27, 2019
 */
public class DuplicateUserException extends TecnicalPaymentSystemException{

	public DuplicateUserException() {
	}

	public DuplicateUserException(String message) {
		super(message);
	}
}
