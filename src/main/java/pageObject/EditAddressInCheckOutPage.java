package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cucumber.api.java.en.Then;
import utility.WebDriverUtils;

public class EditAddressInCheckOutPage extends AbstractPage {

	@FindBy(xpath="//button[@class='action action-show-popup']")
	protected WebElement btn_addAddress;
	
	@FindBy(xpath="//input[@name='firstname']")
	protected WebElement txt_firstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	protected WebElement txt_lastName;
	
	@FindBy(xpath="//input[@name='street[0]']")
	protected WebElement txt_street0;
	
	@FindBy(xpath="//input[@name='street[1]']")
	protected WebElement txt_street1;
	
	@FindBy(xpath="//input[@name='telephone']")
	protected WebElement txt_telephone;
	
	@FindBy(xpath="//select[@name='region_id']")
	protected WebElement select_region;
	
	@FindBy(xpath="//select[@name='country_id']")
	protected WebElement select_country;
	
	@FindBy(xpath="//button[@class='action primary action-save-address']")
	protected WebElement btn_saveAddress;
	
	public EditAddressInCheckOutPage(final WebDriverUtils driver){
		
		super(driver);
	}
	
	@Then("user add address in checkout funnel with First Name (.*), Last Name (.*), Phone (.*), First Address (.*), Second Address (.*), Area (.*), and Country (.*)")
	public void addAddressInCheckoutFunnel(final String firstName, final String lastName, final String phone, final String firstAddress, final String secondAddress, final String area, final String country) {
		
		
		driver.clickButton(btn_addAddress);
		driver.explicitWait();
		
		driver.getWebDriver_existing().switchTo().activeElement();
		driver.explicitWait();
		
		driver.fillin_textarea(txt_firstName, firstName);
		driver.explicitWait();

		driver.fillin_textarea(txt_lastName, lastName);
		driver.explicitWait();
		
		driver.fillin_textarea(txt_street0, firstAddress);
		driver.explicitWait();
		
		driver.fillin_textarea(txt_street1, secondAddress);
		driver.explicitWait();
		
		driver.fillin_textarea(txt_telephone, secondAddress);
		driver.explicitWait();
		
		driver.select_selectorByText(select_region, area);
		driver.explicitWait();
		
		driver.select_selectorByText(select_country, country);
		driver.explicitWait();
		
		driver.clickButton(btn_saveAddress);
		driver.explicitWait();
	}
}
