package AutoTest.AutoTest;

import org.openqa.selenium.By;

import pageObject.SearchPage;

/**
 * 
 *
 */
public class RealDevices_WebAppTest_VLV extends WebAppTest
{	
	public void search() {
		
		driver.clickLink(By.xpath("//div[@class='header-minimal-search visible-xs visible-sm']/a"));
		driver.explicitWait();
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.search("bag");
	}
}
