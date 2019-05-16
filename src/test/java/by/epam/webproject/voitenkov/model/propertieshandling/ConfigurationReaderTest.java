package by.epam.webproject.voitenkov.model.propertieshandling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import by.epam.webproject.voitenkov.util.propertieshandling.ConfigurationReader;

class ConfigurationReaderTest {

	@Test
	void getProperty() {
		
		String expected = "root";
		String actual = ConfigurationReader.getProperty("DBUserName");
		
		assertEquals(expected, actual);
	}

}
