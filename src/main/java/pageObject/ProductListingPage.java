package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AndroidFindBy;
import junit.framework.Assert;
import utility.Checker;
import utility.IOUtils;
import utility.WebDriverUtils;

public class ProductListingPage extends AbstractPage {

	@AndroidFindBy(xpath = "(//*[contains(@resource-id, 'tv_brand')])[1]")
	@FindBy(how = How.XPATH, using = "(.//descendant::div[@class='result-content']/a[@class='result'])[1]")
	private WebElement link_product;

	@FindBy(how = How.ID, using ="zoo-sticky-header")
	private WebElement div_category;
	
	@FindBy(how = How.ID, using = "shop-sidebar-left")
	private WebElement div_sidebar;
	
	@FindBy(how = How.ID, using = "sorter")
	private WebElement selector_sorter;
	
	public ProductListingPage(final WebDriverUtils driver) {
		super(driver);
	}
	
	@And("user clicks product (.*)")
	public void clickProduct(final String productName) {
		
		driver.clickLink(By.xpath("//a[@class='product-item-link' and contains(text(),'" + productName + "')]"));
		driver.explicitWait();
	}
	
	public void clickProduct(final int productIndex) {
		
		driver.clickButton(link_product);
		driver.explicitWait();
	}
	
	@When("user clicks categories (.*)")
	public void clickCategory(final String categoryPath) {
		
		List<String> categories = Lists.newArrayList(categoryPath.split(";"));
		if(categories.size() > 0) {
			
			driver.clickLink(driver.findElement(By.xpath("(//a[@title='" + categories.get(0).trim() + "'])[1]")));
			driver.explicitWait();
			
			if(categories.size() > 1) {
				driver.clickLink(driver.findElement(By.xpath("//a[contains(text(), '"+ categories.get(categories.size()-1).trim()+"')]")));
				driver.explicitWait();
			}
		}
	}
	
	@And("user selects sort option (.*)")
	public void clickSortOption(final String sortOption) {
		
		driver.select_selectorByText(selector_sorter, sortOption);
		driver.explicitWait();
	}
	
	@And("user selects filter option (.*)")
	public void clickFilterOption(final String filterOption) {
		
		List<String> filterOptions = Lists.newArrayList(filterOption.split(":"));
		if(filterOptions.size() > 0) {
			
			driver.clickLink(driver.findElement(By.xpath("//li[@data-label='" + filterOptions.get(filterOptions.size()-1).trim() + "']/a")));
			driver.explicitWait();
		}
	}
	
	@Then("all products should be sorted based on sort option (.*)")
	public void validateSorting(final String sortOption) {
		
		if(sortOption.equalsIgnoreCase("price")||sortOption.equalsIgnoreCase("價錢") || sortOption.equalsIgnoreCase("价格")) {
			
			List<Double> sortedPrices = Lists.newArrayList();	
			List<WebElement> elems = driver.findElements(By.xpath("//span[@class='price']"));
			for(WebElement elem: elems) {
				
				String text = elem.getText();

				if(!Checker.isBlank(text)) {
					Double price = IOUtils.extractNumFromString(text);
					sortedPrices.add(price);
					System.out.println(price);
				}
			}
			Assert.assertEquals("product prices are not sorted", true, Ordering.natural().isOrdered(sortedPrices));
		}else if(sortOption.equalsIgnoreCase("product name")
				/*
				|| sortOption.equalsIgnoreCase("商品名稱")|| sortOption.equalsIgnoreCase("商品名称")
				*/) {
			
			List<String> sortedPrices = Lists.newArrayList();
			List<WebElement> elems = driver.findElements(By.xpath("//a[@class='product-item-link']"));
			for(WebElement elem: elems) {
				
				String text = elem.getText();
				if(!Checker.isBlank(text)) {
					sortedPrices.add(text);
					System.out.println(text);
				}
			}
			Assert.assertEquals("product names are not sorted", true, Ordering.natural().isOrdered(sortedPrices));
		}
	}

	@Then("all products should be filtered based on filter option (.*)")
	public void validateFiltering(final String filterOption) {
		
		int i = 0;
		if(filterOption.contains("Out Stock")) {
			List<WebElement> products = driver.findElements(By.xpath("//div[@id='zoo-product-listing']/descendant::li"));
			for(WebElement product: products) {
				WebElement status = product.findElement(By.xpath(".//descendant::div[@class='stock unavailable']"));
				System.out.println("checking i:" + i++);
				Assert.assertNotNull(status);
			}
		}
	}

	@When("^prices of products in product listing page should have 2 decimal places$")
	public void checkPriceDisplay() {
		
		HomePage home = new HomePage(driver);
		home.checkPriceDisplay();
	}
	
}
