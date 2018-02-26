package scrapper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.IOUtils;
import utility.WebDriverUtils;

public class KeyFinder {

	public void findKey() {
		
		try {
			WebDriverUtils driver = new WebDriverUtils();
			driver.openURL("https://pccw:Pccw@!23@uat.habbitzz.com/");
			
			By by = By.cssSelector("[href]");
			List<WebElement> elems = driver.findElements(by);
			System.out.println(elems.size());
			for(int i = 0; i < elems.size(); i ++){
				String URL = driver.getAttributeValue(by, i, "href");
				final String url_in_utf8 = IOUtils.toUTF8String(URL); 
				System.out.println("url:" + url_in_utf8);
					
			}
			
		}catch(Exception e) {
			
		}
	}
	
	public static void main(String[] args) {

		KeyFinder finder = new KeyFinder();
		finder.findKey();
	}

}
