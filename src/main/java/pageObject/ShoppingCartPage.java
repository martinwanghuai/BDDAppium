package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.google.common.collect.Lists;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AndroidFindBy;
import junit.framework.Assert;
import utility.IOUtils;
import utility.WebDriverUtils;

public class ShoppingCartPage extends AbstractPage {
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'btn_checkout')]")
	@FindBy(how = How.ID, using = "top-cart-btn-checkout")
	protected WebElement btn_checkOut;
	
	@FindBy(how = How.XPATH, using = "//button[@class='increase items items-count']")
	protected WebElement btn_IncreaseQty;
	
	@FindBy(how = How.XPATH, using = "//button[@class='reduced items  items-count']")
	protected WebElement btn_DecreaseQty;
		
	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'cart-item')]")
	protected WebElement txt_qty;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='actions'])[1]/a")
	protected WebElement btn_View;
	
	@FindBy(how = How.XPATH, using = "//div[@id='block-discount']/div[@class='title']")
	protected WebElement btn_Link;

	@FindBy(how = How.ID, using = "coupon_code")
	protected WebElement txt_Coupon;
	
	@FindBy(how = How.XPATH, using = "//button[@value='APPLY DISCOUNT']")
	protected WebElement btn_ApplyCoupon;
	
	@FindBy(how = How.XPATH, using = "//div[@data-bind='html: message.text']")
	protected WebElement txt_CouponSuccessMsg;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-raised action cancel primary']")
	protected WebElement btn_RemoveCoupon;
	
	@FindBy(how = How.XPATH, using = "//div[@class='action showcart']/a")
	protected WebElement btn_shoppingCart;
	
	public ShoppingCartPage(final WebDriverUtils driver) {
		super(driver);
	}
	
	@And("user checkout shopping cart")
	public void checkOut() {
		
		driver.clickButton(btn_checkOut);
		driver.explicitWait();
	}
	
	@Then("shopping cart should include (.*)")
	public void validateProductsInShoppingCart(final String productsInShoppingCart) {
		
		System.out.println("validate products in shopping cart");
		List<String> products = Lists.newArrayList(productsInShoppingCart.split(";"));
		
		List<WebElement> actualProducts_web = Lists.newArrayList(driver.findElements(By.xpath("(//ol[@id='mini-cart'])[1]/descendant::a[@class='product-item-photo']")));
		driver.explicitWait();
		
		List<String> actualProducts = Lists.newArrayList();
		for(WebElement actualProduct: actualProducts_web) {
			actualProducts.add(driver.getAttributeValue(actualProduct, "title"));
		}
		System.out.println("Actual products:" + actualProducts);
		for(String product: products) {
			
			boolean containProduct = false;
			for(String actualProduct: actualProducts) {
				if(actualProduct.contains(product)) {
					containProduct = true;
					break;
				}
			}
			
			Assert.assertTrue("Shopping cart does not has product:"+ product, containProduct);
		}
		driver.explicitWait();
	}
	
	@When("user click shopping cart")
	public void clickShoppingCart() {
	
		driver.clickButton(btn_shoppingCart);
		driver.explicitWait();
	}
	
	@When("user view shopping cart")
	public void viewShoppingCart() {
		
		driver.clickButton(btn_View);
		driver.explicitWait();
	}
	
	@When("user set qty in shopping cart as (.*)")
	public void setQtyInShoppingCart(final String qty) {
		
		ProductPage product = new ProductPage(driver);
		product.adjustQty(Integer.parseInt(qty), txt_qty, btn_IncreaseQty, btn_DecreaseQty);
		driver.explicitWait();
	}
	
	@And("user set promo code (.*)")
	public void setPromoCode(final String promoCode) {
		
		driver.clickButton(btn_Link);
		driver.explicitWait();
		
		driver.fillin_textarea(txt_Coupon, promoCode);
		driver.explicitWait();
		
		driver.clickButton(btn_ApplyCoupon);
		driver.explicitWait();
	}
	
	@When("user remove promo code")
	public void removePromoCode() {
		
		driver.clickButton(btn_Link);
		driver.explicitWait();
		
		driver.clickButton(btn_RemoveCoupon);
		driver.explicitWait();
	}
	
	@Then("user should see a success msg (.*) on shopping cart page")
	public void validateCouponSuccessMsg(final String msg) {
		
		String actualMsg = txt_CouponSuccessMsg.getText();
		System.out.println(actualMsg);
		driver.explicitWait();
		Assert.assertEquals("Coupon success msg is inconsistent:", msg , actualMsg);
		driver.explicitWait();
	}
	
	@When("user remove the product (.*) from shopping cart")
	public void removeProductFromShoppingCart(final String product) {
		
		WebElement elem = driver.findElement(By.xpath("//tr[td[1]/descendant::a[contains(text(),'" + product +"')]]/td[4]/descendant::a[@title='Remove item']"));
		driver.clickButton(elem);
		driver.explicitWait();
		
	}
	
	@Then("shopping cart qty should be consistent with counter number")
	public void validateShoppingCartQty() {
		
		WebElement elem = driver.findElement(By.xpath("//span[@class='counter-number']"));
		System.out.println(elem.getText());
		final int counterNum = Integer.parseInt(elem.getText());
		System.out.println("Counter number:" + counterNum);
		
		if(counterNum > 0) {
			List<WebElement> elems = driver.findElements(By.xpath("//tr[@class='item-info']/td[2]"));
			int sum = 0;
			for(WebElement qty: elems) {
				int qty1 = Integer.parseInt((qty.getText()));
				System.out.println(qty1);
				sum += qty1;
			}
			
			Assert.assertEquals("shopping cart qty is inconsistent with counter number", counterNum, sum);
		}else if(counterNum == 0){
			
			WebElement empty_ShoppingCart = driver.findElement(By.xpath("//div[@class='cart-empty']"));
			Assert.assertTrue("Shopping cart is not empty while counter number is zero", empty_ShoppingCart != null);
			
		}else {
			
			Assert.fail("Counter number is negative");
		}
		
		driver.explicitWait();
	}
	
	@Then("qty in shopping cart should be (.*)")
	public void validateQtyInShoppingCart(final String qty) {
		
		Assert.assertEquals("Qty in shopping cart is wrong", qty, driver.getValue(txt_qty).trim());
		driver.explicitWait();
	}

	@And("price in shopping cart should be correct")
	public void validatPriceInShoppingCart() {
		
		List<WebElement> prices = driver.findElements(By.xpath("(//ol[@id='mini-cart'])[1]/descendant::span[@class='price']"));
		Double sum = 0.0; 
		int i = 0;
		for(WebElement price: prices) {
			System.out.println(i++ + ":" + price.getText());
			sum += IOUtils.extractNumFromString(price.getText());
		}
		
		Double total = IOUtils.extractNumFromString(driver.findElement(By.xpath("//div[@class='subtotal']/descendant::span[@class='price']")).getText());
		Assert.assertEquals("Total price is inconsistent:", sum, total);
		driver.explicitWait();
	}
	
}
