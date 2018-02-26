package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.WebDriverUtils;


public class ForgotPasswordPage extends AbstractPage {

	@CacheLookup
	@FindBy(how = How.ID, using="email_address")
	private WebElement txt_email;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//button[@class='btn action submit ']")
	private WebElement btn_submit;
	
	@CacheLookup
	@FindBy(xpath = "//div[@data-ui-id='message-success']/div")
	private WebElement msg_contactUs;
	
	public ForgotPasswordPage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	@When("^user prefers to send email to (.+)$")
	public void forgotPassword(final String email) {
		
		driver.fillin_textbox(txt_email, email);
		driver.explicitWait();
		
		driver.clickButton(btn_submit);
		driver.explicitWait();
	}
	
	@Then("^user should receive password reset confirmation msg (.*)$")
	public void validateResetPasswordInfo(final String confirmMsg) {
		
		driver.explicitWait();
		String actualMsg = msg_contactUs.getText();
		Assert.assertEquals(actualMsg, confirmMsg);
	}

}
