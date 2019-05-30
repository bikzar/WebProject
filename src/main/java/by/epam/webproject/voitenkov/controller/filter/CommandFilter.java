package by.epam.webproject.voitenkov.controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.webproject.voitenkov.controller.command.comandenum.CommandType;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.util.ConstantConteiner;

/**
 * @author Sergey Voitenkov
 *
 *         May 30, 2019
 */
public class CommandFilter implements Filter {

	private static final List<String> ADMINISTRATION_COMMANDS = Arrays.asList(
			CommandType.ADD_CARD.name(), 
			CommandType.DELETE_CARD.name(),
			CommandType.FIND_USER.name(), 
			CommandType.GO_TO_UNLOCK_PAGE.name(),
			CommandType.LOAD_SEARCH_FORM.name(),
			CommandType.LOAD_UNLOCK_PAGE.name(),
			CommandType.LOCK_ACCOUNT.name(), 
			CommandType.UNLOCK_ACCOUNT.name(),
			CommandType.UNLOCK_CARD.name());

	private final static List<String> USER_COMMANDS = Arrays.asList(
			CommandType.CALCULATE_COMMISSION.name(),
			CommandType.GO_TO_PAY_PAGE.name(),
			CommandType.GO_TO_PAYMENT_HISTORY.name(),
			CommandType.GO_TO_REPLENISH_PAGE.name(),
			CommandType.GO_TO_RESULT_PAGE.name(),
			CommandType.LOAD_BANK_ACCOUNT.name(),
			CommandType.LOAD_PAY_HISTORY.name(), 
			CommandType.PAY.name(),
			CommandType.REPLENISH_ACCOUNT.name());

	private final static List<String> COMMON_COMMANDS = Arrays.asList(
			CommandType.CHANGE_LOCALE.name(), 
			CommandType.DEFAULT.name(),
			CommandType.GO_TO_LOGIN_PAGE.name(), 
			CommandType.LOG_OUT.name(),
			CommandType.LOGIN.name(), 
			CommandType.REGISTRATION.name(),
			CommandType.REGISTRATION_PAGE.name());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("user");

		String command = request.getParameter("command");

		if (command != null && !isCommonCommand(command) && user != null) {

			if (user.isAdmin() && isAdministrationCommand(command)) {
				chain.doFilter(request, response);
			} else if (!user.isAdmin() && isUserCommand(command)) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(ConstantConteiner.PATH_TO_INDEX_FOR_REDIRECT);
			}
		} else {

			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	private boolean isAdministrationCommand(String command) {
		return ADMINISTRATION_COMMANDS.contains(command.toUpperCase());
	}

	private boolean isUserCommand(String command) {
		return USER_COMMANDS.contains(command.toUpperCase());
	}

	private boolean isCommonCommand(String command) {
		return COMMON_COMMANDS.contains(command.toUpperCase());
	}
}
