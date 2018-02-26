package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import junit.framework.Assert;
import utility.Checker;
import utility.WebDriverUtils;

public class HomePage extends AbstractPage {

	public static final String PRICE_DISPLAY = "^(\\$)*(.*)(\\d{2})$";
	
	@AndroidFindBy(xpath="//*[contains(@resource-id, 'navigation_account')]")
	@iOSFindBy(xpath ="//XCUIElementTypeTabBar/XCUIElementTypeButton[4]")
	@FindBy(xpath="(//li[@data-label='or']/a)[1]")
	private WebElement btn_Account;

	@iOSFindBy(xpath ="//XCUIElementTypeTabBar/XCUIElementTypeButton[2]")
	@AndroidFindBy(xpath="//*[contains(@resource-id, 'navigation_category')]")
	@FindBy(how = How.ID, using = "search")
	private WebElement btn_Category;

	@FindBy(xpath="//a[@class='action create']")
	private WebElement link_Signup;
	
	@FindBy(xpath="//a[@class='action remind forgot-pass']")
	private WebElement link_ForgotPassword;
	
	@FindBy(xpath="//div[@class='footer-top-col-group']/div[1]/descendant::li[1]/a")
	private WebElement link_ContactUs;

	@FindBy(xpath="//div[@class='footer-top-col-group']/div[1]/descendant::li[2]/a")
	private WebElement link_FAQ;

	@FindBy(xpath="//div[@class='footer-top-col-group']/div[1]/descendant::li[3]/a")
	private WebElement link_Shipping;
	
	@FindBy(xpath="//div[@class='footer-top-col-group']/div[1]/descendant::li[4]/a")
	private WebElement link_Refund;
	
	@FindBy(xpath="//div[@class='footer-top-col-group']/div[2]/descendant::li[3]/a")
	private WebElement link_Terms;

	@FindBy(xpath="//div[@class='footer-top-col-group']/div[2]/descendant::li[4]/a")
	private WebElement link_Privacy;
	
	@FindBy(xpath = "(//li[@class='customer-welcome']/span[@class='customer-name']/a)[1]")
	private WebElement link_Account;

	@FindBy(xpath = "(//li[@class='nav item']/a)[1]")
	private WebElement link_EditAccount;
	
	@FindBy(xpath = "(//li[@class='nav item']/a)[2]")
	private WebElement link_EditAddress;
	
	@FindBy(xpath = "(//li[@class='nav item']/a)[3]")
	private WebElement link_MyOrder;
	
	@FindBy(how = How.ID, using = "newsletter")
	private WebElement text_newsletter;
	
	@FindBy(xpath = "//button[@class='btn-novetty action subscribe ']")
	private WebElement btn_newsletter;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menuAccount']/li[2]/a")
	private WebElement btn_logout;
	
	@FindBy(xpath = "//a[contains(text(), 'English')]")
	private WebElement link_EN;
	
	@FindBy(xpath = "//a[contains(text(), '简')]")
	private WebElement link_SC;
	
	@FindBy(xpath = "//a[contains(text(), '繁')]")
	private WebElement link_TC;
	
	public HomePage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	public void gotoCategory() {
		
		driver.clickButton(btn_Category);
		driver.explicitWait();
	}
	
	
	public void gotoAccount() {

		driver.clickButton(btn_Account);
		driver.explicitWait();
	}
	
	public void gotoSignUp() {
		
		this.gotoAccount();
		
		driver.clickLink(link_Signup);
		driver.explicitWait();
	}
	
	public void gotoForgotPassword() {
		
		this.gotoAccount();
		
		driver.clickLink(link_ForgotPassword);
	}
	
	@Given("user set lang (.*)")
	public void setLang(final String lang) {
		
		switch(lang.toLowerCase()) {
		case "en":
			if(driver.isElementPresent(By.xpath("//a[contains(text(), 'English')]"))) {
				System.out.println("Change lang: EN" );
				driver.clickLink(link_EN);	
			}
			break;
		case "sc":
			if(driver.isElementPresent(By.xpath("//a[contains(text(), '简')]"))) {
				System.out.println("Change lang: SC" );
				driver.clickLink(link_SC);	
			}
			break;
		case "tc":
			if(driver.isElementPresent(By.xpath("//a[contains(text(), '繁')]"))) {
				System.out.println("Change lang: TC" );
				driver.clickLink(link_TC);	
			}
			break;
		default:
				break;
		}
		driver.explicitWait();
	}
	
	@Given("^user navigates to (.+) page$")
	public void user_navigates_to_page(String page) throws Throwable {

		driver.openURL("https://pccw:Pccw@!23@uat.habbitzz.com/");
        driver.explicitWait();

		switch(page.toLowerCase()) {
		case "login":
			this.gotoAccount();
			break;
		case "signup":
			this.gotoSignUp();
			break;
		case "search":
		case "category":
			this.gotoCategory();
			break;
		case "forgot password":
			this.gotoForgotPassword();
			break;
		case "home":
			break;
		default:
			this.gotoLink(page);
			break;
		}
	}
	
	public void gotoLink(final String link) {
		
		switch(link.toLowerCase()) {
		case "faq":
			driver.clickLink(link_FAQ);
			break;
		case "shipping":
			driver.clickLink(link_Shipping);
			break;
		case "refund":
			driver.clickLink(link_Refund);
			break;
		case "terms":
			driver.clickLink(link_Terms);
			break;
		case "privacy":
			driver.clickLink(link_Privacy);
			break;
		case "account":
			driver.clickLink(link_Account);
			driver.explicitWait();
			driver.clickLink(link_EditAccount);
			break;
		case "address":
			driver.clickLink(link_Account);
			driver.explicitWait();
			driver.clickLink(link_EditAddress);
			break;
		case "order":
			driver.clickLink(link_Account);
			driver.explicitWait();
			driver.clickLink(link_MyOrder);
			break;
		case "contact-us":
			driver.clickLink(link_ContactUs);
		default:
			break;
		}
		driver.explicitWait();
	}
	
	@When("^prices of products in homepage should have 2 decimal places$")
	public void checkPriceDisplay() {
		
		
		List<WebElement> elems = driver.findElements(By.xpath("//span[@class='price']"));
		for(WebElement elem: elems) {
			
			String text = elem.getText();
			if(!Checker.isBlank(text)) {
				System.out.println(text);
				Assert.assertTrue(text.matches(PRICE_DISPLAY));	
			}
		}
	}
	
	@When("^user provides subscribe email (.*)$")
	public void sendSubscribeEmail(final String email) {
		
		driver.fillin_textbox(text_newsletter, email);
		driver.explicitWait();
		
		driver.clickButton(btn_newsletter);
		driver.explicitWait();
	}
	
	@Then("^user should receive a confirm email (.*)$")
	public void receiveConfirmEmail(String email) throws Throwable {
		
	}
	
	@Then("user logout")
	public void logout() {
		
		driver.clickButton(btn_logout);
		driver.explicitWait();
	}
	
}
