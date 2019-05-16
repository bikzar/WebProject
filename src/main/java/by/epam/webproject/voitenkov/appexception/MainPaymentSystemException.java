package by.epam.webproject.voitenkov.appexception;

/**
 * @author Sergey Voitenkov
 *
 * Apr 14, 2019
 */
public class MainPaymentSystemException extends Throwable {

	public MainPaymentSystemException() {
	}

	public MainPaymentSystemException(String message) {
		super(message);
	}

	public MainPaymentSystemException(Throwable cause) {
		super(cause);
	}

	public MainPaymentSystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
