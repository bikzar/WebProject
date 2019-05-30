package by.epam.webproject.voitenkov.model.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.model.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public interface BankAccountService {

	void loadBankAccount(HttpServletRequest req) throws ServiceLevelException;
	
	void loadPayForm(HttpServletRequest req) throws ServiceLevelException ;

	boolean loadUnBlockPage(HttpServletRequest req) throws ServiceLevelException;

	boolean lockBankAccount(HttpServletRequest req)	throws ServiceLevelException;

	boolean unLockBankaccount(HttpServletRequest req) throws ServiceLevelException;
}
