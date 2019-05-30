package by.epam.webproject.voitenkov.controller.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.controller.command.comandenum.CommandType;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 24, 2019
 */
public class CommandDispatcher {

	private static Map<String, Command> commandMap;

	static {

		commandMap = new HashMap<String, Command>();

		for (CommandType command : CommandType.values()) {
			commandMap.put(command.name(), command.getCommand());
		}
	}

	public static Command getCommand(HttpServletRequest req) {

		Command result = null;
		
		String commandStr;
		
		if (req != null && (commandStr = (req.getParameter(ConfigurationReader
				.getProperty(ConstantConteiner.COMMAND)))) != null && !commandStr.isEmpty()) {

			result = commandMap.get(commandStr.toUpperCase());
		}
		
		if(result == null) {
			result = commandMap.get(ConfigurationReader
					.getProperty(ConstantConteiner.GO_TO_LOGIN_PAGE).toUpperCase());
		}

		return result;
	}
}
