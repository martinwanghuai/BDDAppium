package pageObject;


import utility.WebDriverUtils;

public class SearchPage_Mobile extends AbstractPage {

	public SearchPage_Mobile(final WebDriverUtils driver) {
		
		super(driver);
	}
	
	
	public void search(final String searchTxt) {
		
		SearchPage search = new SearchPage(driver);
		driver.clickButton(search.btn_search);
		driver.explicitWait();
		
		driver.fillin_textbox(search.txt_search, searchTxt);
		driver.explicitWait();
		
	}
}
