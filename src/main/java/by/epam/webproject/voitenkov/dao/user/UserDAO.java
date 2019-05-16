package by.epam.webproject.voitenkov.dao.user;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 27, 2019
 */
public interface UserDAO {

	/**
	 * @param userLogin
	 * @return userID number or -1 if userLogin is null or user with userLogin
	 *         doesn't exist
	 * @throws CantCreateConnectinPoolException
	 * @throws DaoException
	 */
	public int getUserIDByLogin(String userLogin) throws DaoException;

	/**
	 * @return null - if userLogin is null or user with userLogin doesn't exist.
	 * @throws DaoException
	 * @throws CantCreateConnectinPoolException
	 */
	public String getUserPassword(String userLogin) throws DaoException;
}
