package by.epam.webproject.voitenkov.model.builder.formbuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergey Voitenkov
 *
 * Apr 25, 2019
 */
public interface FromFormBuilder<T> {
	
	/**
	 * @param req - request from form
	 * @return Object from form
	 */
	public T build(HttpServletRequest req);
}
