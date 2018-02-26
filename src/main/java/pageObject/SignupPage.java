package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import utility.WebDriverUtils;

public class SignupPage extends AbstractPage {

	@CacheLookup
	@FindBy(how = How.ID, using = "prefix")
	protected WebElement selector_prefix;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "firstname")
	protected WebElement txt_firstname;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "lastname")
	protected WebElement txt_lastname;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "email_address")
	protected WebElement txt_email;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "password")
	protected WebElement txt_pwd_signup;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "password-confirmation")
	protected WebElement txt_pwd_confirm;

	@CacheLookup
	@FindBy(how = How.ID, using = "is_subscribed")
	protected WebElement checkbox_subscribed;
	
	@CacheLookup
	@FindBy(how = How.NAME, using = "persistent_remember_me")
	protected WebElement checkbox_rememberMe;

	@CacheLookup
	@FindBy(how = How.NAME, using = "read_tnc")
	protected WebElement checkbox_tnc;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//button[@class='btn action submit btn-signup']")
	protected WebElement btn_signUp;
	
	public SignupPage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	@When("^user signup with the following info$")
	public void signup(DataTable valueList) throws Throwable {
	  
		List<DataTableRow> rows = valueList.getGherkinRows();
		this.signup(rows.get(0).getCells().get(1), 
				rows.get(1).getCells().get(1), 
				rows.get(2).getCells().get(1), 
				rows.get(3).getCells().get(1), 
				rows.get(4).getCells().get(1), 
				Boolean.parseBoolean(rows.get(5).getCells().get(1)),
				Boolean.parseBoolean(rows.get(6).getCells().get(1)),
				Boolean.parseBoolean(rows.get(7).getCells().get(1)));
	}

	@Then("^signup should be successful$")
	public void validateSignup() throws Throwable {
	}

	
	protected void signup(final String prefix, final String firstName, final String lastName, final String email, final String pwd, boolean subscribe,
			final boolean rememberMe, final boolean TAC) {
		
		driver.select_selectorByValue(selector_prefix, prefix);
		driver.explicitWait();
		
		driver.fillin_textbox(txt_firstname, firstName);
		driver.explicitWait();

		driver.fillin_textbox(txt_lastname, lastName);
		driver.explicitWait();
		
		driver.fillin_textbox(txt_email, email);
		driver.explicitWait();
		
		driver.fillin_textbox(txt_pwd_signup, pwd);
		driver.explicitWait();
		
		driver.fillin_textbox(txt_pwd_confirm, pwd);
		driver.explicitWait();
		
		driver.check_checkbox(checkbox_subscribed);
		driver.explicitWait();
		
		driver.check_checkbox(checkbox_rememberMe);
		driver.explicitWait();
		
		driver.check_checkbox(checkbox_tnc);
		driver.explicitWait();
		
		driver.clickButton(btn_signUp);
		driver.explicitWait();
	}
}
