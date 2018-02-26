package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.WebDriverUtils;

public class AbstractPage {
	
	public WebDriverUtils driver;
	
	public AbstractPage() {
		
	}
	
	public AbstractPage(final WebDriverUtils driver1) {
	
		this.driver = driver1;
		if(driver1.getWebDriver_existing() instanceof AppiumDriver) {
			PageFactory.initElements(new AppiumFieldDecorator(driver1.getWebDriver_existing()), this);
		}else{
			PageFactory.initElements(driver.getWebDriver_existing(), this);	
		}
	}
}
