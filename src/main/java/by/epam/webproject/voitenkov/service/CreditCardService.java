package by.epam.webproject.voitenkov.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 * May 15, 2019
 */
public interface CreditCardService {
	boolean blockCreditCard(HttpServletRequest request)  throws ServiceLevelException;
}
