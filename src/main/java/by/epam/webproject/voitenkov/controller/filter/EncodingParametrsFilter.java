package by.epam.webproject.voitenkov.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.epam.webproject.voitenkov.util.ConstantConteiner;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 27, 2019
 */
public class EncodingParametrsFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(req.getCharacterEncoding() == null) {
			req.setCharacterEncoding(ConstantConteiner.UTF_8_ENCODING);
		}
			
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
