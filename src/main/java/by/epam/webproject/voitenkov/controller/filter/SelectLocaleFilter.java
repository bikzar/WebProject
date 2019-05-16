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
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		Locale locale = req.getLocale();
		
		if(locale != null && locale.getLanguage().compareTo(ConstantConteiner.RU_LANGUAGE_ABRIV)==0) {
			Locale.setDefault(new Locale(ConstantConteiner.RU_LANGUAGE_ABRIV));
		}else {
			Locale.setDefault(Locale.FRANCE);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
