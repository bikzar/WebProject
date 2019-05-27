package by.epam.webproject.voitenkov.model.dal.dao.bankaccount;

import by.epam.webproject.voitenkov.model.dal.dao.daoexception.DaoException;

public interface BankAccountDAO{

	public boolean deleteById(long bankAccountId) throws DaoException;

	boolean updateIsBlockColumnById(long id, boolean isLock) throws DaoException;
}
