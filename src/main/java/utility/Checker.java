package utility;

public class Checker {

	public static final boolean isBlank(final String s) {

		return s == null || s.trim().equals("") || s.equalsIgnoreCase("null");
	}
}
