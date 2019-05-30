package by.epam.webproject.voitenkov.util.validator.user.uservalidatorexception;

import by.epam.webproject.voitenkov.appexception.LogicPaymentSystemException;

/**
 * @author Sergey Voitenkov
 *
 * May 27, 2019
 */
public class DuplicateUserLoginException extends LogicPaymentSystemException {

	public DuplicateUserLoginException() {
	}

	public DuplicateUserLoginException(String message) {
		super(message);
	}
}
