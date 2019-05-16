package by.epam.webproject.voitenkov.service;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.dao.daoexception.DuplicateUserException;
import by.epam.webproject.voitenkov.service.serviceexception.CantRegistredUserException;

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
	public boolean registration(HttpServletRequest req) throws CantRegistredUserException, DuplicateUserException;
	
	public boolean logIn(HttpServletRequest req) throws DaoException;
	
	public void logOut(HttpServletRequest req);
}
