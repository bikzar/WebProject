package by.epam.webproject.voitenkov.model.service.serviceexception;

import by.epam.webproject.voitenkov.appexception.TecnicalPaymentSystemException;

/**
 * @author Sergey Voitenkov
 *
 *         May 14, 2019
 */
public class ServiceLevelException extends TecnicalPaymentSystemException {

	public ServiceLevelException() {
	}

	public ServiceLevelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLevelException(String message) {
		super(message);
	}

	public ServiceLevelException(Throwable cause) {
		super(cause);
	}
}
