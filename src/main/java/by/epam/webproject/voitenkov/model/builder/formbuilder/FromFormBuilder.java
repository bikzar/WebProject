package by.epam.webproject.voitenkov.model.builder.formbuilder;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.model.builder.builderexception.CantBuildException;

/**
 * @author Sergey Voitenkov
 *
 * Apr 25, 2019
 */
public interface FromFormBuilder<T> {
	
	/**
	 * @param req - request from form
	 * @return Object from form
	 * @throws CantBuildException 
	 */
	public T build(HttpServletRequest req) throws CantBuildException;
}
