package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import utility.WebDriverUtils;


public class LoginPage extends AbstractPage{
	
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
	
	public LoginPage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	@When("^user logs in using Username (.+) and Password (.+)$")
	public void login(final String name, final String pwd) {
		
		if(!driver.getCurrentUrl().endsWith("/customer/account/login")) {
			driver.clickButton(By.xpath("//ul[@class='header links user_area']/descendant::a"));
			driver.explicitWait();
		}
		
		driver.fillin_textbox(txt_userName, name);
		driver.explicitWait();

		driver.fillin_textbox(txt_pwd, pwd);
		driver.explicitWait();
		
		driver.clickButton(btn_login);
		driver.explicitWait();
	}
	
	@Then("^login should be successful$")
	public void login_should_be_successful() throws Throwable {

	}
}
