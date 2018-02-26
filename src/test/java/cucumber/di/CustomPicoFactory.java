package cucumber.di;

import cucumber.runtime.java.picocontainer.PicoFactory;
import utility.WebDriverUtils;

public class CustomPicoFactory extends PicoFactory {

	public CustomPicoFactory() {
		addClass(WebDriverUtils.class);
	}
}
