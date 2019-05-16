package by.epam.webproject.voitenkov.model.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import by.epam.webproject.voitenkov.model.entity.BankAccount;
import by.epam.webproject.voitenkov.model.entity.CreditCard;
import by.epam.webproject.voitenkov.model.entity.User;
import by.epam.webproject.voitenkov.model.entity.enumeration.CurrencyType;

public class ExpectedUser {

	public static User getExpectedUser() {

		CreditCard creditCard = new CreditCard(4, false, CurrencyType.USD, 4);

		LinkedList<CreditCard> creditList = new LinkedList<CreditCard>();

		creditList.add(creditCard);

		BankAccount bankAccount = new BankAccount(4, false, 966,
				CurrencyType.USD, 2, creditList);

		ArrayList<BankAccount> bankAccList = new ArrayList<BankAccount>();

		bankAccList.add(bankAccount);

		LocalDate birthDate = LocalDate.of(1990, 1, 21);

		User user = new User(2, "Сергей", "Старостенко", birthDate, false,
				"starStn", "456", bankAccList);

		return user;
	}
}
