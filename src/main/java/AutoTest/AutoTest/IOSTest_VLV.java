package AutoTest.AutoTest;

import org.openqa.selenium.By;
import utility.Constant;

public class IOSTest_VLV extends AndroidTest_VLV{
	
	public void login() throws Exception {
		
		driver.explicitWait();
		driver.clickButton(By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[4]"));
		driver.explicitWait(4*Constant.EXPLICITWAIT_MILLIS);
		
		driver.clickButton(By.xpath("//XCUIElementTypeButton[@name='SIGN IN' or @name='登入']"));
		driver.explicitWait();
		
		driver.fillin_textbox(By.xpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[@name='Email Address' or @name='電郵地址']]/XCUIElementTypeTextField"), "sb@sb.com");
		driver.explicitWait();
		
		driver.fillin_textbox(By.xpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[@name='Password' or @name='密碼']]/XCUIElementTypeSecureTextField"), "12345678");
		driver.explicitWait();
		
		driver.clickButton(By.xpath("//XCUIElementTypeButton[@name='SIGN IN' or @name='登入']"));
		driver.explicitWait();
	}
	
	public void search() throws Exception {
		
		driver.clickButton(By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[2]"));
		driver.explicitWait();
		
		driver.clickButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name='magnifyingGlass']"));
		driver.explicitWait();
		
		driver.fillin_textbox(By.xpath("(//XCUIElementTypeSearchField[@name='Search' or @name='搜尋'])[1]"), "abc");
		driver.explicitWait();
	}
	
	public void addToCart() throws Exception {
		
		driver.clickButton(By.xpath("(//XCUIElementTypeCell[1])/XCUIElementTypeImage"));
		driver.explicitWait();
	}
	
}
