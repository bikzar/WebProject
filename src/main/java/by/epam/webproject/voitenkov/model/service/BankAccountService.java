package by.epam.webproject.voitenkov.model.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public interface BankAccountService {

	public void loadBankAccount(HttpServletRequest req) throws ServiceLevelException;
	
	public void loadPayForm(HttpServletRequest req) throws ServiceLevelException ;

	void loadUnBlockPage(HttpServletRequest req) throws ServiceLevelException;

	boolean lockBankAccount(HttpServletRequest req)	throws ServiceLevelException;

	boolean unLockBankaccount(HttpServletRequest req) throws ServiceLevelException;
}