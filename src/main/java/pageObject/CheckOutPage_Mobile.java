package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utility.Constant;
import utility.WebDriverUtils;

public class CheckOutPage_Mobile extends AbstractPage {

	@AndroidFindBy(xpath= "//*[contains(@resource-id, 'expiryMonth')]")
	protected WebElement btn_expiryMonth;
	
	@AndroidFindBy(xpath= "//*[contains(@resource-id, 'expiryYear')]")
	protected WebElement btn_expiryYear;
	
	@AndroidFindBy(xpath= "//*[contains(@resource-id, 'cardHolderName')]")
	protected WebElement txt_cardName;
	
	@AndroidFindBy(xpath= "//*[contains(@content-desc, 'Pay now')]")
	protected WebElement btn_PayMe;
	
	
	public CheckOutPage_Mobile(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	public void checkOutCartWithMPES(final String cardNo, final String expiryMonth, final String expiryYear) {
		
		CheckOutPage checkout = new CheckOutPage(driver);
		driver.explicitWait();
		driver.clickButton(checkout.btn_checkout_Step1);
		driver.explicitWait();
		
		driver.clickButton(checkout.btn_checkout_Step2_MPES);
		driver.explicitWait(4*Constant.EXPLICITWAIT_MILLIS);
		
		//input card no.
		driver.fillin_textbox(checkout.txt_cardNo, cardNo);
		driver.explicitWait();
		
		//input expire month & expire year
		driver.clickButton(btn_expiryMonth);

		driver.clickButton(By.xpath("(//*[contains(@resource-id, 'text1')])[6]"));

		driver.clickButton(btn_expiryYear);

		driver.clickButton(By.xpath("(//*[contains(@resource-id, 'text1')])[7]"));

		driver.fillin_textbox(txt_cardName, "a");

		driver.fillin_textbox(checkout.txt_secureNo, "100");

		((AndroidDriver)driver.getWebDriver_existing()).scrollToExact("Pay now");
		
		driver.clickButton(btn_PayMe);

		driver.clickButton(checkout.btn_Submit);

		driver.clickButton(checkout.btn_Continue);
	}

}
