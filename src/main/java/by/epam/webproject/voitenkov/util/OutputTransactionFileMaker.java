package by.epam.webproject.voitenkov.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.webproject.voitenkov.model.entity.Transaction;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 *         May 20, 2019
 */
public class OutputTransactionFileMaker {

	private static Logger logger = LogManager.getLogger();

	private OutputTransactionFileMaker() {
	}

	public static boolean prepareOutSideTransaction(Transaction transaction) {

		boolean result = true;

		String path = ConfigurationReader
				.getProperty(ConstantConteiner.SERIALIZATION_PATH)
				+ transaction.getDestinationId();

		ObjectOutputStream oStream = null;

		File file = new File(path);

		try {

			FileOutputStream fos = new FileOutputStream(file);

			try {

				oStream = new ObjectOutputStream(fos);
				oStream.writeObject(transaction);

			} catch (IOException e) {
				logger.error(
						"Can't create ObjectOutputStream or can't serialize transaction.\n"
								+ transaction);
				result = false;
			}

		} catch (FileNotFoundException e) {
			logger.error("File not found: " + path);
			result = false;
		} finally {

			if (oStream != null) {
				try {
					oStream.close();
				} catch (IOException e) {
					logger.error("Can't close ObjectOutputStream.");
				}
			}
		}

		return result;
	}

}
