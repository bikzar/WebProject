package by.epam.webproject.voitenkov.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 *         May 8, 2019
 */
public interface BankAccountService {

	public void loadBankAccount(HttpServletRequest req) throws ServiceLevelException;
	
	public void loadPayForm(HttpServletRequest req) throws ServiceLevelException ;
}
