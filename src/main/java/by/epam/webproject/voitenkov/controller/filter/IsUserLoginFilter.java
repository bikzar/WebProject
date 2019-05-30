package by.epam.webproject.voitenkov.controller.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         3 мая 2019 г.
 */
public class IsUserLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String currentPath = req.getRequestURI();

		String regex = ConfigurationReader
				.getProperty(ConstantConteiner.URL_REGEX_FIRST_ENTER);

		String contentRegex = ConfigurationReader
				.getProperty(ConstantConteiner.CONTENT_REGEX);

		boolean isAvailablePath = Pattern.matches(regex, currentPath);

		boolean isContentPath = Pattern.matches(contentRegex, currentPath);

		String logInComand = ConfigurationReader
				.getProperty(ConstantConteiner.LOGIN_COMMAND);

		String goToRegPageCommand = ConstantConteiner.GO_REGISTRATION_COMMAND;

		String registrationCommand = ConfigurationReader
				.getProperty(ConstantConteiner.REGISTRATION_COMMAND);

		String defaultCommand = ConfigurationReader
				.getProperty(ConstantConteiner.DEFAULT_COMMAND);

		String currentCommand = req.getParameter(
				ConfigurationReader.getProperty(ConstantConteiner.COMMAND));

		boolean isCommand = false;

		if (currentCommand != null) {
			isCommand = currentCommand.equalsIgnoreCase(registrationCommand)
					|| currentCommand.equalsIgnoreCase(goToRegPageCommand)
					|| currentCommand.equalsIgnoreCase(logInComand)
					|| currentCommand.equalsIgnoreCase(defaultCommand);
		}

		HttpSession session = req.getSession(false);

		boolean isSessionActive = session != null
				&& (session.getAttribute(ConfigurationReader
						.getProperty(ConstantConteiner.USER))) != null;

		if (isSessionActive && isAvailablePath || isCommand && isAvailablePath
				|| isContentPath || isAvailablePath && !isSessionActive
						&& currentCommand == null) {

			if (isSessionActive && isAvailablePath && currentCommand == null) {

				User user = (User) session.getAttribute(ConfigurationReader
						.getProperty(ConstantConteiner.USER));

				if (!user.isAdmin()) {

					req.getRequestDispatcher(ConfigurationReader
							.getProperty(ConstantConteiner.USER_PAGE))
							.forward(request, response);
				} else {

					req.getRequestDispatcher(ConfigurationReader
							.getProperty(ConstantConteiner.ADMIN_PAGE))
							.forward(request, response);
				}

			} else {

				chain.doFilter(request, response);
			}
		} else {

			((HttpServletResponse) response)
					.sendRedirect(ConstantConteiner.PATH_TO_INDEX_FOR_REDIRECT);
		}
	}

	@Override
	public void destroy() {
	}
}
