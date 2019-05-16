package by.epam.webproject.voitenkov.util.propertieshandling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.util.ConstantConteiner;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 14, 2019
 */
public class ConfigurationReader {

	public ConfigurationReader() {
	}

	private static final String CONFIG_FILE_NAME = ConstantConteiner.CONFIG_FILE_NAME;

	private static Logger logger;
	private static Properties property;

	static {

		logger = LogManager.getLogger("ConfigurationReader");

		property = new Properties();

		FileInputStream fileInputStream = null;

		try {

			fileInputStream = new FileInputStream(new ResourceFilePathMaker().getPath(CONFIG_FILE_NAME));

			property.loadFromXML(fileInputStream);

		} catch (InvalidPropertiesFormatException e) {
			logger.warn(
					"Properties Format invalid in file: " + CONFIG_FILE_NAME,
					e);
		} catch (FileNotFoundException e) {
			logger.warn("Try to load configuration file. Can't find file: "
					+ CONFIG_FILE_NAME, e);
		} catch (IOException e) {
			logger.warn(
					"Try to load property from XML file: " + CONFIG_FILE_NAME,
					e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.warn("Try to close FileInpurStream", e);
				}
			}
		}

	}

	public static String getProperty(String key) {

		String resualt = "";

		if (key != null) {
			resualt = property.getProperty(key);
		}

		return resualt;
	}

}
