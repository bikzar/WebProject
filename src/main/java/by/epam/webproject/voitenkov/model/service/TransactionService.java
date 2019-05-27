package by.epam.webproject.voitenkov.model.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 *         May 11, 2019
 */
public interface TransactionService {

	public boolean payOperation(HttpServletRequest request)
			throws ServiceLevelException;

	public void calculateCommision(HttpServletRequest request)
			throws ServiceLevelException;

	boolean makeReplenishOperation(HttpServletRequest request)
			throws ServiceLevelException;

	public void loadHistory(HttpServletRequest request)
			throws ServiceLevelException;
}
