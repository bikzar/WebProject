package by.epam.webproject.voitenkov.controller.command;

import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 28, 2019
 */
public class CommandResult {

	private static final String defaultPath = ConfigurationReader
			.getProperty(ConstantConteiner.LOGIN_PAGE);

	private String path;
	private boolean isForwardAction;

	public CommandResult() {
		path = defaultPath;
	}

	public CommandResult(String path) {
		
		this.path = path != null && !path.isEmpty() ? path : defaultPath;
	}

	public CommandResult(String path, boolean isForwardAction) {
		this(path);
		this.isForwardAction = isForwardAction;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {

		if (path != null && !path.isEmpty()) {
			this.path = path;
		}

	}

	public boolean isForvardAction() {
		return isForwardAction;
	}

	public void setForvardAction(boolean isForvardAction) {
		this.isForwardAction = isForvardAction;
	}
}
