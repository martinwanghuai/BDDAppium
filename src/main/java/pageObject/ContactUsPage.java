package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.WebDriverUtils;

public class ContactUsPage extends AbstractPage {

	@CacheLookup
	@FindBy(how = How.ID, using = "name")
	private WebElement txt_name;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "email")
	private WebElement txt_email;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "telephone")
	private WebElement txt_phone;
	
	@CacheLookup
	@FindBy(xpath="//textarea[@id='comment']")
	private WebElement txt_comment;
	
	@CacheLookup
	@FindBy(xpath = "//button[@class='btn-novetty btn-raised primary']")
	private WebElement btn_submit;
	
	@CacheLookup
	@FindBy(xpath = "//div[@data-ui-id='message-success']/div")
	private WebElement msg_contactUs;
	
	public ContactUsPage(WebDriverUtils driver) {
		
		super(driver);
	}

	@When("^user provides contact-us info like name (.*), email (.*), phone (.*), question (.*)$")
	public void provideContactUsInfo(final String name, final String email, final String phone, final String comment) {
		
		driver.fillin_textbox(txt_name, name);
		driver.explicitWait();
		
		driver.fillin_textbox(txt_email, email);
		driver.explicitWait();
		
		driver.fillin_textbox(txt_phone, phone);
		driver.explicitWait();
		
		driver.fillin_textarea(txt_comment, comment);
		driver.explicitWait();
		
		driver.clickButton(btn_submit);
		driver.explicitWait();
	}
	
	@Then("^user should receive a contact-us confirm msg (.*)$")
	public void validateContactUsInfo(final String confirmMsg) {
		
		driver.explicitWait();
		String actualMsg = msg_contactUs.getText();
		Assert.assertEquals(actualMsg, confirmMsg);
	}
	
}
