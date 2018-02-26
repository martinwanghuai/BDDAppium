package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import utility.WebDriverUtils;

public class MyOrderPage extends AbstractPage {

	@CacheLookup
	@FindBy(how=How.XPATH, using="")
	WebElement link_order;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//div[@class='seller-order-detail']/ul/li[1]/span")
	WebElement text_simpleStatus;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//div[@class='seller-order-detail']/ul/li[2]/span")
	WebElement text_simpleShippingMethod;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//div[@class='box box-order-shipping-method']/div")
	WebElement text_shippingMethod;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[contains(@id, 'order-item-row')]/td[@class='col name']/div[@class='order-item-product-name']/strong")
	WebElement text_productName;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[contains(@id, 'order-item-row')]/td[@class='col qty']/descendant::span[@class='content']")
	WebElement text_productQty;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[contains(@id, 'order-item-row')]/td[@class='col subtotal']/descendant::span[@class='price']")
	WebElement text_price;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[@class='subtotal']/descendant::span[@class='price']")
	WebElement text_subTotal;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[@class='shipping']/descendant::span[@class='price']")
	WebElement text_shipping;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[@class='discount']/descendant::span[@class='price']")
	WebElement text_discount;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//tr[@class='grand_total']/descendant::span[@class='price']")
	WebElement text_total;
	
	public MyOrderPage(final WebDriverUtils driver) {
		super(driver);
	}
	
	@Then("^user clicks order (\\d+) and checkes order status (.+), shipping method (.+), product name (.+), product qty (\\d+), sub-total (.*), shipping (.*), discount (.*), and total (.*)$")
	public void checkOrder(final String orderId, String status, String shippingMethod, String productName, String productQty, String subTotal, String shipping, String discount, String total) {
		
		link_order = driver.findElement(By.xpath("(//tr[td[@class='col id' and text()='" + orderId +"']]/td[@class='col actions']/a)[1]"));
		driver.clickLink(link_order);
		driver.explicitWait();

		//Check status
		Assert.assertTrue(status.toLowerCase().contains(text_simpleStatus.getText().toLowerCase()));
		
		//Check shipping method
		Assert.assertTrue(shippingMethod.toLowerCase().contains(text_simpleShippingMethod.getText().toLowerCase()));
		Assert.assertTrue(shippingMethod.equalsIgnoreCase(text_shippingMethod.getText()));
		
		//Check product name, qty, price
		Assert.assertTrue(text_productName.getText().contains(productName));
		Assert.assertEquals(productQty,text_productQty.getText());
		
		System.out.println("expected sub total:" + subTotal);
		System.out.println("actual sub total:" + text_price.getText());
		Assert.assertEquals(subTotal,text_price.getText());
		
		//check price calculation
		Assert.assertEquals(subTotal, text_subTotal.getText());
		
		Assert.assertTrue(shipping.contains(text_shipping.getText()));
		
		Assert.assertTrue(discount.contains(text_discount.getText()));
		
		Assert.assertTrue(total.contains(text_total.getText()));
	}
}
