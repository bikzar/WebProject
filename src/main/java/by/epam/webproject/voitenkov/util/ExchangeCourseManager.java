package by.epam.webproject.voitenkov.util;

import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 * May 20, 2019
 */
public class ExchangeCourseManager {

	private ExchangeCourseManager() {
	}
	
	public static double getCourse(CurrencyType currencyType, String prefixConstant) {

		double result = 0;

		String courseTmp = prefixConstant + currencyType;

		if (courseTmp != null) {

			result = Double
					.parseDouble(ConfigurationReader.getProperty(courseTmp));
		}

		return result;
	}
}
