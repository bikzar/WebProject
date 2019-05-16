/**
 * 
 */
package by.epam.webproject.voitenkov.appexception;

/**
 * @author Sergey Voitenkov
 *
 * Apr 14, 2019
 */
public class TecnicalPaymentSystemException extends MainPaymentSystemException {

	public TecnicalPaymentSystemException() {
	}

	public TecnicalPaymentSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public TecnicalPaymentSystemException(String message) {
		super(message);
	}

	public TecnicalPaymentSystemException(Throwable cause) {
		super(cause);
	}
}
