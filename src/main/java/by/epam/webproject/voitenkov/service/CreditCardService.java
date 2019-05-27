package by.epam.webproject.voitenkov.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 * May 15, 2019
 */
public interface CreditCardService {
	boolean lockCreditCard(HttpServletRequest request)  throws ServiceLevelException;

	boolean unLockCreditCard(HttpServletRequest request) throws ServiceLevelException;

	boolean addCard(HttpServletRequest req) throws ServiceLevelException;

	boolean deleteCrad(HttpServletRequest req) throws ServiceLevelException;
}
