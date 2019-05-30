package by.epam.webproject.voitenkov.model.builder.builderexception;

import by.epam.webproject.voitenkov.appexception.LogicPaymentSystemException;

/**
 * @author Sergey Voitenkov
 *
 * May 29, 2019
 */
public class CantBuildException extends LogicPaymentSystemException {

	public CantBuildException() {
	}

	public CantBuildException(String message) {
		super(message);
	}

}
