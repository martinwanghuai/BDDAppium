package pageObject;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.collections.Lists;
import org.testng.collections.Sets;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.pagefactory.AndroidFindBy;
import junit.framework.Assert;
import utility.Checker;
import utility.IOUtils;
import utility.WebDriverUtils;

public class ProductPage extends AbstractPage {

	@FindBy(how = How.XPATH, using = "//div[@class='custom-qty']/button[contains(@class,'increase')]")
	protected WebElement btn_addQty;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'btn_add_to_bag')]")
	@FindBy(how = How.ID, using = "product-addtocart-button")
	protected WebElement btn_addToCart;
	
	@FindBy(how = How.XPATH, using = "//div[@class='zoo-breadcrumbs']")
	protected WebElement link_breadCrumb;
	
	@FindBy(how = How.XPATH, using = "//div[@class='product-categories-name']/span")
	protected WebElement txt_category;
	
	@FindBy(how = How.XPATH, using = "//div[@class='product-return-info']/ul[@class='product-return-points']/li[1]")
	protected WebElement txt_returnLabel;
	
	@FindBy(how = How.XPATH, using = "//div[@class='product-return-info']/ul[@class='product-return-points']/li[2]")
	protected WebElement txt_guaranteedReturnLabel;
	
	@FindBy(how = How.ID, using = "qty")
	protected WebElement txt_qty;
	
	@FindBy(how = How.XPATH, using = "//button[@class='increase items items-count']")
	protected WebElement btn_IncreaseQty;
	
	@FindBy(how = How.XPATH, using = "//button[@class='reduced items  items-count']")
	protected WebElement btn_DecreaseQty;
	
	@FindBy(how = How.ID, using = "product-addtocart-button")
	protected WebElement btn_AddToCart;
	
	@FindBy(how = How.ID, using = "tab-label-product.info.description")
	protected WebElement btn_details;
	
	@FindBy(how = How.ID, using = "tab-label-additional")
	protected WebElement btn_additional;
	
	
	public ProductPage(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	public void setQuantity(final int quantity) {
		for(int i = 0; i < quantity; i ++) {
			driver.clickButton(btn_addQty);
			driver.explicitWait();
		}
	}
	
	public void addToCart() {
		
		driver.clickButton(btn_addToCart);
		driver.explicitWait();
	}
	
	@Then("prices of config. products should change with config. options")
	public void validateConfigProductPrice() {
		
		System.out.println("check config products price");
		List<Double> prices = Lists.newArrayList(); 
		List<WebElement> childProducts = driver.findElements(By.xpath("//div[contains(@class,'swatch-option color')]"));
		
		for(WebElement childProduct: childProducts) {
			driver.clickLink(childProduct);
			driver.explicitWait();
			
			String price =  driver.findElement(By.xpath("(//span[@class='price'])[1]")).getText();
			System.out.println(price);
			prices.add(IOUtils.extractNumFromString(price));
		}
		Set<Double> set = Sets.newHashSet(prices);
		Assert.assertEquals("Child products share the same price", set.size(), prices.size());
		driver.explicitWait();
	}
	
	@And("breadcrumb should be (.*)")
	public void validateBreadcrumb(final String breadcrumbPath) {
		
		List<WebElement> elems = link_breadCrumb.findElements(By.xpath(".//div/ul[@class='items']/li"));
		StringBuilder breadcrumbs = new StringBuilder();
		for(WebElement elem: elems) {
			String path = elem.findElement(By.xpath(".//*[self::a or self::span]")).getText().trim();
			breadcrumbs.append(path).append(";");
		}
		Assert.assertEquals("Breadcrumb does not match", breadcrumbPath, breadcrumbs.toString());
		driver.explicitWait();
	}
	
	@And("category should be (.*)")
	public void validateCategory(final String category) {
		
		Assert.assertEquals("Category does not match", category, txt_category.getText().trim());
		driver.explicitWait();
	}
	
	@And("it should show return label (.*) and (.*)")
	public void validateLabel(final String returnLabel, final String guaranteedReturnLabel) {
		
		Assert.assertEquals("Return label does not match", returnLabel, txt_returnLabel.getText().trim());
		Assert.assertEquals("Guaranted Return label does not match", guaranteedReturnLabel, txt_guaranteedReturnLabel.getText().trim());
		driver.explicitWait();
	}
	
	@And("user should be able to set qty as (.*)")
	public void setQtyInProductPage(final String qty) {
		
		this.adjustQty(Integer.parseInt(qty), txt_qty, btn_IncreaseQty, btn_DecreaseQty);
		Assert.assertEquals("Cannot set qty", qty, driver.getValue(txt_qty).trim());
		driver.explicitWait();
	}
	
	@Then("user add the product into shopping cart")
	@And("user should be able to add into shopping cart")
	public void addProductIntoShoppingCart() {
		
		driver.clickButton(btn_AddToCart);
		driver.explicitWait();
	}
	
	@And("product details should not be empty")
	public void validateProductDetails() {
		
		boolean emptyProductDetails = true;
		driver.clickButton(btn_details);
		driver.explicitWait();
		
		List<WebElement> elems = driver.findElements(By.xpath("//div[@id='product.info.description']/span|//div[@id='product.info.description']/span/a"));
		System.out.println("validate product details");
		System.out.println("size:"+ elems.size());
		for(WebElement elem: elems) {
			String str = elem.getText().trim();
			System.out.println(str);
			if(!Checker.isBlank(str)) {
				emptyProductDetails = false;
				break;
			}
		}
		Assert.assertEquals("Product details are empty", false, emptyProductDetails);
	}
	
	@And("additional product details should not be empty")
	public void validateAdditionalProductDetails() {
		
		boolean emptyAdditionalDetails = true;
		driver.clickButton(btn_additional);
		driver.explicitWait();
		
		List<WebElement> elems = driver.findElements(By.xpath("//table[@id='product-attribute-specs-table']/descendant::tr"));
		System.out.println("validate additional product details");
		System.out.println("size:"+ elems.size());
		for(WebElement elem: elems) {
			String str = elem.findElement(By.xpath(".//th")).getText().trim();
			System.out.println(str);
			if(!Checker.isBlank(str)) {
				emptyAdditionalDetails = false;
				break;
			}
		}
		Assert.assertEquals("Additional product details are empty", false, emptyAdditionalDetails);
	}
	
	public void adjustQty(final int targetQty, WebElement txt_qty, WebElement btn_IncreaseQty, WebElement btn_DecreaseQty) {
		
		int qty = Integer.parseInt(driver.getValue(txt_qty).trim());
		
		while(qty < targetQty) {
			driver.clickButton(btn_IncreaseQty);
			driver.explicitWait();
			qty = Integer.parseInt(driver.getValue(txt_qty).trim());
		}
		
		while(qty > targetQty) {
			driver.clickButton(btn_DecreaseQty);
			driver.explicitWait();
			qty = Integer.parseInt(driver.getValue(txt_qty).trim());
		}
	}
}
