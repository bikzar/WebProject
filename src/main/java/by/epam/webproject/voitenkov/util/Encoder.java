package by.epam.webproject.voitenkov.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sergey Voitenkov
 *
 *         May 29, 2019
 */
public class Encoder {

	private static Logger logger = LogManager.getLogger();

	private Encoder() {
	}

	public static String encodeToUTF8(String text) {

		String result = "";

		if (text != null) {

			try {
				
				result = new String(text.getBytes(StandardCharsets.ISO_8859_1),
						ConstantConteiner.UTF_8_ENCODING);
				
			} catch (UnsupportedEncodingException e) {
				
				logger.error("Try convert text to "
						+ ConstantConteiner.UTF_8_ENCODING
						+ " in encodeToUTF8() methood Encoder class.");
			}
		}
		return result;
	}
}
