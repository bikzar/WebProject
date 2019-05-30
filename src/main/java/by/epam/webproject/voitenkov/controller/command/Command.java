package by.epam.webproject.voitenkov.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 18, 2019
 */
public interface Command {
	
	public CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
	
}
