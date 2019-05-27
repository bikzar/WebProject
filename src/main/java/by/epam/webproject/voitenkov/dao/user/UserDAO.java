package by.epam.webproject.voitenkov.dao.user;

import java.util.List;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;
import by.epam.webproject.voitenkov.model.entity.User;

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
	int getUserIDByLogin(String userLogin) throws DaoException;

	/**
	 * @return null - if userLogin is null or user with userLogin doesn't exist.
	 * @throws DaoException
	 * @throws CantCreateConnectinPoolException
	 */
	String getUserPassword(String userLogin) throws DaoException;

	List<User> getUserList(String name, String secondName, String login) throws DaoException;
}