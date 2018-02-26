package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import utility.WebDriverUtils;

public class LoginPage_Mobile extends AbstractPage{

	@iOSFindBy(xpath="//XCUIElementTypeOther[XCUIElementTypeStaticText[@name='Email Address' or @name='電郵地址']]/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[contains(@resource-id, 'et_email')]")
	@FindBy(how = How.NAME, using = "login[username]")
	protected WebElement txt_userName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id, 'et_password')]")
	@FindBy(how = How.NAME, using = "login[password]")
	protected WebElement txt_pwd;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id, 'btn_login')]")
	@FindBy(how = How.ID, using = "send2")
	protected WebElement btn_login;
	
		
	public LoginPage_Mobile(WebDriverUtils driver) {
		super(driver);
	}
	
	public void login(final String name, final String pwd) {
		
		driver.clickButton(btn_login);
		driver.explicitWait();
		
		LoginPage login = new LoginPage(driver);
		login.login(name, pwd);
	}
	
}
