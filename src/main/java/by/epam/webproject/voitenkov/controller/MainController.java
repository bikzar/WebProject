package by.epam.webproject.voitenkov.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.dao.connectionpool.ConnectionPool;
import by.epam.webproject.voitenkov.util.ConstantConteiner;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 17, 2019
 */
public class MainController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		ConnectionPool.getInstance();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		rocessingRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		rocessingRequest(request, response);
	}

	private void rocessingRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nextPage = null;

		Command command = CommandDispatcher.getCommand(request);

		if (command != null) {
			nextPage = command.execute(request, response);
		}

		if (nextPage == null) {
			nextPage = ConfigurationReader
					.getProperty(ConstantConteiner.LOGIN_PAGE);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);

		dispatcher.forward(request, response);

	}

	@Override
	public void destroy() {

		super.destroy();

		ConnectionPool.getInstance().closeConnectionPool();

	}
}
