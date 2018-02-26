package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cucumber.api.java.en.And;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utility.WebDriverUtils;

public class CheckOutPage extends AbstractPage{

	@FindBy(how = How.XPATH, using = "//button[@data-role='proceed-to-checkout']")
	protected WebElement btn_checkout_to_Step1;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'btnNextStep')]")
	@FindBy(how = How.XPATH, using = "//button[@data-role='opc-continue']")
	protected WebElement btn_checkout_Step1;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'btnNextStep')]")
	@FindBy(how = How.ID, using = "tns_hpf")
	protected WebElement btn_checkout_Step2_MPES;
	
	@FindBy(how = How.ID, using = "magenest_stripe_iframe")
	protected WebElement box_checkout_Step2_STRIPE;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'cardNumber')]")
	@FindBy(how = How.XPATH, using = "//input[@type='tel']")
	protected WebElement txt_cardNo;
	
	@FindBy(how = How.ID, using = "tns_hpf_expiration")
	protected WebElement btn_expiryMonth;
	
	@FindBy(how = How.ID, using = "tns_hpf_expiration_yr")
	protected WebElement btn_expiryYear;

	@AndroidFindBy(xpath="//*[contains(@resource-id, 'csc')]")
	@FindBy(how = How.XPATH, using = "//input[@type='tel']")
	protected WebElement txt_secureNo;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc, 'Submit')]")
	@FindBy(how = How.XPATH, using = "(//button[@class='action primary checkout'])[2]")
	protected WebElement btn_Submit;

	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'btnNextStep')]")
	@FindBy(how = How.XPATH, using = "//a[@class=' btn btn-raised action primary continue']")
	protected WebElement btn_Continue;
	
	@FindBy(how = How.XPATH, using = "//button[@class='action action-show-popup']")
	protected WebElement btn_addAddress;
	
	public CheckOutPage(final WebDriverUtils driver) {
		
		super(driver);
	} 
	
	@And("user continue with checkout funnel Step 1")
	public void checkoutStep1() {
	
		driver.clickButton(btn_checkout_Step1);
		driver.explicitWait();
	}
	
	public void checkOutCartWithSTRIPE(final String cardNo, final String expiryMonthAndYear, final String CVC) {
		
		driver.clickButton(btn_checkout_Step1);
		driver.explicitWait();

		driver.clickButton(box_checkout_Step2_STRIPE);
		driver.explicitWait();

		driver.clickButton(btn_Submit);
		driver.explicitWait();
		
		driver.switchToFrame(By.xpath("//iframe[@name='stripe_checkout_app']"));
		driver.explicitWait();
		
		driver.getWebDriver_existing().switchTo().activeElement();
		
		driver.fillin_textbox(By.xpath("//div[@id='container']/descendant::input[@placeholder='Card number']"), cardNo);
		driver.explicitWait();
		
		driver.fillin_textbox(By.xpath("//input[@placeholder='MM / YY']"), expiryMonthAndYear);
		driver.explicitWait();
		
		driver.fillin_textbox(By.xpath("//input[@placeholder='CVC']"), CVC);
		driver.explicitWait();
		
		driver.clickButton(By.xpath("//button[@type='submit' and @class='Button-animationWrapper-child--primary Button Button--transitionBackward']"));
		driver.explicitWait();
	}
	
	@And("user pay money with (.*), (.*), (.*), (.*), (.*), (.*)")
	public void checkOutCartWithMPES(final String payType, final String cardNo, final String expiryYear, final String expiryMonth, final String cardHolder, final String CVC) {
		
		if(payType.toLowerCase().contains("credit card (for website)")) {
			
			driver.clickButton(btn_checkout_Step2_MPES);
			driver.explicitWait();
			
			//input card no.
			driver.switchToFrame(By.xpath("//iframe[@class='gw-proxy-cardNumber']"));
			driver.explicitWait();
			
			driver.fillin_textbox(txt_cardNo, cardNo);
			driver.explicitWait();
			
			driver.switchBackFromFrame();
			driver.explicitWait();
			
			//input expire month & expire year
			System.out.println("Expiry month:" + expiryMonth);
			driver.select_selectorByValue(btn_expiryMonth, expiryMonth);
			driver.explicitWait();
			
			System.out.println("Expiry year:" + expiryYear);
			driver.select_selectorByValue(btn_expiryYear, expiryYear);
			driver.explicitWait();
			
			//input secure code
			driver.switchToFrame(By.xpath("//iframe[@class='gw-proxy-securityCode']"));
			
			driver.fillin_textbox(txt_secureNo, CVC);
			driver.explicitWait();
			
			driver.switchBackFromFrame();
			 	
			//submit
			driver.clickButton(btn_Submit);
			driver.explicitWait(6000);
		}
	}
	
	public void checkOutCartWithMPES(final String cardNo, final String expiryMonth, final String expiryYear) {
		
		driver.explicitWait();
		driver.clickButton(btn_checkout_Step1);
		driver.explicitWait();
		
		driver.clickButton(btn_checkout_Step2_MPES);
		driver.explicitWait();
		
		//input card no.
		driver.switchToFrame(By.xpath("//iframe[@class='gw-proxy-cardNumber']"));
		driver.explicitWait();
		
		driver.fillin_textbox(txt_cardNo, cardNo);
		driver.explicitWait();
		
		driver.switchBackFromFrame();
		driver.explicitWait();
		
		//input expire month & expire year
		driver.select_selectorByValue(btn_expiryMonth, expiryMonth);
		driver.explicitWait();
		
		driver.select_selectorByValue(btn_expiryYear, expiryYear);
		driver.explicitWait();
		
		//input secure code
		driver.switchToFrame(By.xpath("//iframe[@class='gw-proxy-securityCode']"));
		
		driver.fillin_textbox(txt_secureNo, "100");
		driver.explicitWait();
		
		driver.switchBackFromFrame();
		 	
		//submit
		driver.clickButton(btn_Submit);
		driver.explicitWait(6000);
		
		//continue shopping
		driver.clickButton(btn_Continue);
		driver.explicitWait();
	}
	
	
}
