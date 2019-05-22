package by.epam.webproject.voitenkov.util;

import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

/**
 * @author Sergey Voitenkov
 *
 * May 20, 2019
 */
public class Calculator {

	private Calculator() {
	}
	
	public static double getCommision(double transactionSum) {
		double result = 0;

		String commisionTmp = ConfigurationReader
				.getProperty(ConstantConteiner.TRANSACTION_COMMISION);

		double commision = Double.parseDouble(commisionTmp);

		result = transactionSum * commision;

		result = Math.round(result * 100) / 100.0;

		return result;
	}
	
	public static double getTransactionSum(CreditCard source, CreditCard destination,
			double sum) {

		double result = 0;

		if (source != null && destination != null & sum > 0) {

			double buyCourse = ExchangeCourseManager.getCourse(source.getCyrrencyType(),
					ConstantConteiner.BUY_BYN_CONST);

			double salesCourse = ExchangeCourseManager.getCourse(destination.getCyrrencyType(),
					ConstantConteiner.SALE_BYN_CONST);

			result = sum * salesCourse / buyCourse;

			result = Math.round(result * 100) / 100.0;
		}

		return result;
	}
	
	public static double roundTwoDecimalPlace(double sum) {
		return Math.round(sum*100.0)/100.0;
	}
}
