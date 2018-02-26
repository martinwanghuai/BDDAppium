package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cucumber.api.java.en.Then;
import utility.WebDriverUtils;

public class EditAddressPage extends AbstractPage {
	
	@FindBy(how = How.ID, using="telephone")
	private WebElement text_phone;
	
	@FindBy(how = How.ID, using="street_1")
	private WebElement text_address1;
	
	@FindBy(how = How.ID, using="street_2")
	private WebElement text_address2;
	
	@FindBy(how = How.ID, using="region_id")
	private WebElement select_region;
	
	@FindBy(how = How.ID, using="country")
	private WebElement select_country;
	
	@FindBy(how = How.ID, using="primary_billing")
	private WebElement checkBox_primary_billing;
	
	@FindBy(how = How.ID, using="primary_shipping")
	private WebElement checkBox_primary_shipping;
	
	@FindBy(xpath="//button[@class='action primary add']")
	private WebElement button_addAddress;

	@FindBy(xpath="//button[@class='action submit primary']")
	private WebElement button_saveAddress;
	
	@FindBy(xpath="(//li[@class='item'])[1]/descendant::a[1]")
	private WebElement link_editAddress;
	
	public EditAddressPage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	@Then("^user updates first address with Title (.+), First Name (.+), Last Name (.+), Phone (\\d+), First Address (.+), Second Address (.+), Area (.+), Country (.+), Default Billing Address (.+), and Default Delivery Address (.+)$")
	public void updateFirstAddress(final String title, final String firstName, final String lastName, final String phone, 
			final String addressLine1, final String addressLine2, final String area, final String country, 
			final boolean defaultBillingAddress, final boolean defaultDeliveryAddress) {
		
		driver.clickLink(link_editAddress);
		driver.explicitWait();
		
		this.updateAccountDetail(title, firstName, lastName, phone, addressLine1, addressLine2, area, country, 
				defaultBillingAddress, defaultDeliveryAddress);
	}
	
	@Then("^user adds address with Title (.+), First Name (.+), Last Name (.+), Phone (\\d+), First Address (.+), Second Address (.+), Area (.+), Country (.+), Default Billing Address (.+), and Default Delivery Address (.+)$")
	public void addAddress(final String title, final String firstName, final String lastName, final String phone, 
			final String addressLine1, final String addressLine2, final String area, final String country, 
			final boolean defaultBillingAddress, final boolean defaultDeliveryAddress) {
		
		driver.clickButton(button_addAddress);
		driver.explicitWait();

		updateAccountDetail(title, firstName, lastName, phone, addressLine1, addressLine2, area, country,
				defaultBillingAddress, defaultDeliveryAddress);
	}
	
	@Then("^user deletes address (\\d+)$")
	public void deleteAddress(final String addressIndex) {
		
		driver.clickButton(By.xpath("(//li[@class='item'])["+ addressIndex + "]/descendant::a[2]"));
		driver.explicitWait();
		
		((JavascriptExecutor)driver.getWebDriver_existing()).executeScript("window.confirm = function(msg){return true;};");
	}

	protected void updateAccountDetail(final String title, final String firstName, final String lastName,
			final String phone, final String addressLine1, final String addressLine2, final String area,
			final String country, final boolean defaultBillingAddress, final boolean defaultDeliveryAddress) {
		
		EditAccountPage editAccount = new EditAccountPage(driver);
		editAccount.editAccountDetail(title, firstName, lastName);

		driver.fillin_textbox(text_phone, phone);
		driver.explicitWait();
		
		driver.fillin_textbox(text_address1, addressLine1);
		driver.explicitWait();
		
		driver.fillin_textbox(text_address2, addressLine2);
		driver.explicitWait();
		
		driver.select_selectorByValue(select_region, area);
		driver.explicitWait();
		
		driver.select_selectorByValue(select_country, country);
		driver.explicitWait();
		
		System.out.println("default billing address:" + defaultBillingAddress);
		if(defaultBillingAddress) {
			driver.check_checkbox(checkBox_primary_billing);	
		}else {
			driver.uncheck_checkbox(checkBox_primary_billing);
		}
		driver.explicitWait();
		
		System.out.println("default delivery address:" + defaultDeliveryAddress);
		if(defaultDeliveryAddress) {
			driver.check_checkbox(checkBox_primary_shipping);
		}else {
			driver.uncheck_checkbox(checkBox_primary_shipping);
		}
		driver.explicitWait();
		
		driver.clickButton(button_saveAddress);
		driver.explicitWait();
	}
	
}
