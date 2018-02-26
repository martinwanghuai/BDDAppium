package AutoTest.AutoTest;

import org.testng.annotations.Test;
import pageObject.CheckOutPage;
import pageObject.LoginPage;
import pageObject.ProductListingPage;
import pageObject.ProductPage;
import pageObject.SearchPage;
import pageObject.ShoppingCartPage;

public class WebAppTest extends BasicTestNGTest 
{
	@Test(invocationCount = 2)
	public void test1() {

		driver.openURL("https://pccw:Pccw@!23@uat.habbitzz.com/");
        this.login(username, pwd);
		this.search();
		this.addToCart();
		this.checkOutCart();
	}
	
	public void search() {
		
		SearchPage search = new SearchPage(driver);
		search.search("th");
	}
	
	public void login(String userName, String pwd) {
		
		LoginPage login = new LoginPage(driver);
		login.login(userName, pwd);
	}

	public void addToCart() {
		
		ProductListingPage productListingPage = new ProductListingPage(driver);
		productListingPage.clickProduct(1);
		
		ProductPage productPage = new ProductPage(driver);
		productPage.addToCart();
		
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.checkOut();
	}
	
	public void checkOutCart() {
		
		CheckOutPage checkoutPage = new CheckOutPage(driver);
//		checkoutPage.checkOutCartWithSTRIPE("4242424242424242", "04/24", "100");
		checkoutPage.checkOutCartWithMPES("5123450000000008", "5", "2021");
	}
}
