package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cucumber.api.java.en.Then;
import utility.WebDriverUtils;

public class EditAccountPage extends AbstractPage {

	@CacheLookup
	@FindBy(how = How.ID, using="prefix")
	private WebElement select_title;
	
	@CacheLookup
	@FindBy(how = How.ID, using="firstname")
	private WebElement text_firstname;

	@CacheLookup
	@FindBy(how = How.ID, using="lastname")
	private WebElement text_lastname;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using="//button[@class='action save primary']")
	private WebElement btn_saveProfile;

	public EditAccountPage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	@Then("^user updates Title (.+), First Name (.+), and Last Name (.+)$")
	public void editAccount(final String title, final String firstName, final String lastName) {
		
		editAccountDetail(title, firstName, lastName);
		
		driver.clickButton(btn_saveProfile);
		driver.explicitWait();
	}

	protected void editAccountDetail(final String title, final String firstName, final String lastName) {
		driver.select_selectorByValue(select_title, title);
		driver.explicitWait();
		
		driver.fillin_textbox(text_firstname, firstName);
		driver.explicitWait();
		
		driver.fillin_textbox(text_lastname, lastName);
		driver.explicitWait();
	}
}
