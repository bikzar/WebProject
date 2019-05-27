package by.epam.webproject.voitenkov.controller.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.webproject.voitenkov.util.ConstantConteiner;

/**
 * @author Sergey Voitenkov
 *
 *         May 1, 2019
 * 
 *         This class set locale. For users with default RU locale set RU locale
 *         with RU interface. For other users it will be EN locale with EN
 *         interface.
 */
public class SelectLocaleFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession(true);
		String language = (String) session.getAttribute(ConstantConteiner.LANGUAGE);
		
		if (language == null || language.isEmpty()) {
			
			String lang = ConstantConteiner.EN_LOCALE;
			
			if(request.getLocale().getLanguage().equals(ConstantConteiner.RU_LOCALE)) {
				lang = ConstantConteiner.RU_LOCALE;
			}
			
			session.setAttribute(ConstantConteiner.LANGUAGE, lang);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
