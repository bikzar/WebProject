package by.epam.webproject.voitenkov.controller.command.implementation;

import by.epam.webproject.voitenkov.controller.command.Command;

/**
 * @author Sergey Voitenkov
 *
 * May 15, 2019
 */
public abstract class AbstractCommand<T> implements Command {
	
	private T service;

	public AbstractCommand(T service) {
		if (service != null) {
			this.service = service;
		}
	}
	
	public T getService() {
		return service;
	}
}
