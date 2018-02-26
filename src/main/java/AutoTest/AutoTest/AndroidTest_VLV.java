package AutoTest.AutoTest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObject.CheckOutPage_Mobile;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.LoginPage_Mobile;
import pageObject.ProductListingPage_Mobile;
import pageObject.ProductPage_Mobile;
import pageObject.SearchPage_Mobile;
import pageObject.ShoppingCartPage;

public class AndroidTest_VLV extends BasicTestNGTest{
	
	
	@Test
	public void test1() throws Exception{

		this.login();
		this.search();
		this.addToCart();
		this.checkOutCart();
	}
	
	public void login() throws Exception{
		
		HomePage homePage = new HomePage(driver);
		homePage.gotoAccount();

		LoginPage_Mobile loginPage = new LoginPage_Mobile(driver);
		loginPage.login("martin.wang@habbitzz.com", "12345678");
	}
	
	public void search() throws Exception{
		
		new HomePage(driver).gotoCategory();

		new SearchPage_Mobile(driver).search("th");
	}
	
	public void addToCart() throws Exception{

		new ProductListingPage_Mobile(driver).clickProduct(1);
		
		new ProductPage_Mobile(driver).addToCart();
	}
	
	public void checkOutCart() throws Exception{
		
		driver.explicitWait();
		
		new ShoppingCartPage(driver).checkOut();
		
		new CheckOutPage_Mobile(driver).checkOutCartWithMPES("5123450000000008", "5", "2021");
		driver.clickButton(By.xpath("//*[contains(@resource-id, 'btnNextStep')]"));
	}
}
