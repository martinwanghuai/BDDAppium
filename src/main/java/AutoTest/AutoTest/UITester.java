package AutoTest.AutoTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.HomePage;
import utility.WebDriverUtils;

public class UITester {

	WebDriverUtils driver = null;
	@BeforeClass
	public void setup() {
		
		driver = new WebDriverUtils();
		
	}
	
	@Test
	public void test1() {
		
		
		driver.openURL("https://pccw:Pccw@!23@uat.habbitzz.com/");
		driver.explicitWait();
		
		driver.clickLink(By.xpath("//div[@class='footer-top-col-group']/div[1]/descendant::li[2]/a"));
		driver.explicitWait();
	}
	
	@Test
	public void test2() {
		
		String str = "$1,320.76";
				String reg = "^(\\$)(.*)(\\.)(\\d{2})$";
				System.out.println(str.matches(reg));
	}
	
	@Test
	public void test3() {
		
		driver.openURL("https://pccw:Pccw@!23@uat.habbitzz.com/contact/index/");
		driver.explicitWait();
		
		WebElement elem = driver.findElement(By.id("comment"));
		elem.sendKeys(Keys.TAB);
		elem.clear();
		elem.sendKeys("Hello World");
		
		driver.explicitWait(10*1000);
	}
	
	@Test
	public void test4() {
		
//		String str = "$1,320.75";
		String str = "$320.75";
		Pattern p = Pattern.compile("(([0-9]+)(\\,))?([0-9]+)(\\.)([0-9]{2})");
		Matcher m = p.matcher(str);
		while(m.find()) {
			double n = Double.parseDouble(m.group().replaceAll("\\,", ""));
			System.out.println(n);
		}
		
		
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.shutDown();
	}
}
