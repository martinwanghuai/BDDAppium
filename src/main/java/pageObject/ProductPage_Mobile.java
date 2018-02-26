package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.pagefactory.AndroidFindBy;
import utility.WebDriverUtils;

public class ProductPage_Mobile extends AbstractPage {

	@AndroidFindBy(xpath = "(//*[contains(@resource-id, 'tv_brand')])[1]")
	protected WebElement link_brand;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'fabCart')]")
	protected WebElement btn_checkout_ShoppingCart;
	

	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'btn_add_to_bag')]")
	@FindBy(how = How.ID, using = "product-addtocart-button")
	protected WebElement btn_addToCart;
	
	public ProductPage_Mobile(final WebDriverUtils driver) {
		super(driver);
	}
	
	
	public void addToCart() {
		
		driver.clickButton(link_brand);
		driver.explicitWait();
		
		driver.clickButton(btn_addToCart);
		driver.explicitWait(500);
		
		driver.clickButton(btn_checkout_ShoppingCart);
		driver.explicitWait();
	}
}
