package by.epam.webproject.voitenkov.dao.bankaccount;

import by.epam.webproject.voitenkov.dao.daoexception.DaoException;

public interface BankAccountDAO{

	public boolean deleteById(long bankAccountId) throws DaoException;

	boolean updateIsBlockColumnById(long id, boolean isLock) throws DaoException;
}
