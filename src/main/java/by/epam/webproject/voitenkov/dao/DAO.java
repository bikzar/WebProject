package by.epam.webproject.voitenkov.dao;

import java.util.List;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;

/**
 * @author Sergey Voitenkov
 *
 *         May 13, 2019
 */
public interface DAO<T> {

	/**
	 * @return Entity or <code>null</code> (if id <= 0 or can't find result).
	 */
	T getById(long id) throws DaoException;

	/**
	 * @return Entity list or Empty entity list (if no connection to DB)
	 * @throws DaoException
	 */
	List<T> getAll(long id) throws DaoException;

	boolean save(T entity) throws DaoException;

	boolean update(T entity) throws DaoException;

	boolean delete(T entity) throws DaoException;
}
