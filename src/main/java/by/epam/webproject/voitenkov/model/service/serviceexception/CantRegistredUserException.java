package by.epam.webproject.voitenkov.model.service.serviceexception;

import by.epam.webproject.voitenkov.appexception.TecnicalPaymentSystemException;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 26, 2019
 */
public class CantRegistredUserException extends TecnicalPaymentSystemException {

	public CantRegistredUserException() {
	}

	public CantRegistredUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public CantRegistredUserException(String message) {
		super(message);
	}

	public CantRegistredUserException(Throwable cause) {
		super(cause);
	}
}
