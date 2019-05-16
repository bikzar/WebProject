package by.epam.webproject.voitenkov.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 * May 11, 2019
 */
public interface TransactionService {
	
	public boolean payOperation(HttpServletRequest request) throws ServiceLevelException;
}
