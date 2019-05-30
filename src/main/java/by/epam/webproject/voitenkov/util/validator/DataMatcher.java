package by.epam.webproject.voitenkov.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Voitenkov
 *
 *         May 27, 2019
 */
public class DataMatcher {

	private DataMatcher() {
	}

	public static boolean match(String regex, String input) {

		boolean result = false;

		if (regex != null & !regex.isEmpty()) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			result = matcher.matches();
		}
		return result;

	}
}
