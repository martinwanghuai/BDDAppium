package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import utility.WebDriverUtils;

public class ProductListingPage_Mobile extends AbstractPage {

	@AndroidFindBy(xpath = "(//*[contains(@resource-id, 'img_search_product')])[1]")
	protected WebElement link_searchSuggestion;
	
	public ProductListingPage_Mobile(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	
	public void clickProduct(final int productIndex) {
		
		ProductListingPage productListing = new ProductListingPage(driver);
		driver.clickLink(link_searchSuggestion);
		driver.explicitWait();
		
		productListing.clickProduct(productIndex);
	}
}
