package by.epam.webproject.voitenkov.util.propertieshandling;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 24, 2019
 */
public class ResourceFilePathMaker {
	
	public String getPath(String filename) {
		return getClass().getClassLoader().getResource("configurations.xml")
				.getPath();
	}
}
