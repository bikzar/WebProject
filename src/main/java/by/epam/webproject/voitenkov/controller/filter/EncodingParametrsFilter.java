package by.epam.webproject.voitenkov.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 27, 2019
 */
public class EncodingParametrsFilter implements Filter {
	
    private final static String ENCODING_PARAMETER = "encoding";
    private String code;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
			
		}
			
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		code = filterConfig.getInitParameter(ENCODING_PARAMETER);
	}

}
