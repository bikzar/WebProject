package by.epam.webproject.voitenkov.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.webproject.voitenkov.controller.command.Command;
import by.epam.webproject.voitenkov.controller.command.CommandDispatcher;
import by.epam.webproject.voitenkov.controller.command.CommandResult;
import by.epam.webproject.voitenkov.model.dal.connectionpool.ConnectionPool;
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

		processingRequest(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processingRequest(request, response);
	}

	private void processingRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CommandResult result = null;
		Command command = CommandDispatcher.getCommand(request);

		if (command != null) {
			result = command.execute(request, response);
		}

		if (result == null) {

			result = new CommandResult();
		}

		if (result.isForvardAction()) {

			RequestDispatcher dispatcher = request
					.getRequestDispatcher(result.getPath());

			dispatcher.forward(request, response);

		} else {

			response.sendRedirect(result.getPath());

		}

	}

	@Override
	public void destroy() {

		super.destroy();

		ConnectionPool.getInstance().closeConnectionPool();

	}
}
