package by.epam.webproject.voitenkov.model.builder.statementbuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sergey Voitenkov
 *
 * Apr 14, 2019
 */
public interface FromStatemantBuilder<T> {
	
	/**
	 * @return null - if resultSet is null or element doesn't exist.
	 * @throws SQLException  if the columnLabel is not valid; if a database
	 *                      access error occurs or this method is called on a
	 *                      closed result set
	 *              
	 */
	public T build(ResultSet resultSet) throws SQLException;
}
