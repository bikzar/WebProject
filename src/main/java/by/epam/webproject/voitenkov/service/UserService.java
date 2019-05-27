package by.epam.webproject.voitenkov.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.dao.daoexception.DuplicateUserException;
import by.epam.webproject.voitenkov.service.serviceexception.CantRegistredUserException;
import by.epam.webproject.voitenkov.service.serviceexception.ServiceLevelException;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 25, 2019
 */
public interface UserService {

	/**
	 * @param
	 * @return userID
	 */
	boolean registration(HttpServletRequest req) throws CantRegistredUserException, DuplicateUserException;
	
	boolean logIn(HttpServletRequest req) throws DaoException;
	
	void logOut(HttpServletRequest req);
	
	void findUser(HttpServletRequest req) throws ServiceLevelException;
}
