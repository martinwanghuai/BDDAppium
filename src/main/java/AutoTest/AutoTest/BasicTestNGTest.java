package AutoTest.AutoTest;

import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import utility.Constant;
import utility.WebDriverUtils;
import utility.WebDriverUtils.Type;

/**For both 
 * 
 * @author martinwang
 *
 */
public class BasicTestNGTest {

	WebDriverUtils driver = null;
	String username, pwd;
	@BeforeMethod(alwaysRun=true)
	@Parameters(value= {"config", "environment", "username","password"})
	public void setUp(String config_file, String environemnt, String username, String pwd) throws Exception {
		
		this.username = username;
		this.pwd = pwd;
		JSONParser parser = new JSONParser();  
		JSONObject config = (JSONObject)parser.parse(new FileReader(Constant.USRDIR + Constant.DIR_TO_CONFIG_FILE + config_file));
		JSONObject envs = (JSONObject)config.get("environments");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		Map<String, String> commonCapabilities = (Map<String, String>)config.get("capabilities");
		Iterator it = commonCapabilities.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		}
		
		Map<String, String> envCapabilities = (Map<String, String>)envs.get(environemnt);
		it = envCapabilities.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		}
		
		String device = (String)capabilities.getCapability("deviceName");
		if(this.isWebDriver(device)) {
			Constant.DEFAULT_BROWSER = utility.WebDriverUtils.Type.valueOf(device);
			driver = new WebDriverUtils();
		}else if(this.isIOSDriver(device)){
			driver = new WebDriverUtils();
			driver.setDriver(new IOSDriver(new URL("http://" + config.get("server")+ "/wd/hub"), capabilities));
		}else {
			driver = new WebDriverUtils();
			driver.setDriver(new AndroidDriver(new URL("http://" + config.get("server") + "/wd/hub"), capabilities));
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception{
		
		driver.shutDown();
	}
	
	
	private boolean isWebDriver(final String device) {
		
		return device.equalsIgnoreCase("firefox") || device.equalsIgnoreCase("chrome") || device.equalsIgnoreCase("ie") 
				|| device.equalsIgnoreCase("safari") ? true: false;
	}
	
	private boolean isIOSDriver(final String device) {
		
		return device.toLowerCase().contains("ios") || device.toLowerCase().contains("iphone");
	}
}
