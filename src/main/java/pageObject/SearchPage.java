package pageObject;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.beust.jcommander.internal.Lists;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utility.WebDriverUtils;

public class SearchPage extends AbstractPage{

	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'search_src_text')]")
	@FindBy(how = How.ID, using = "search")
	public WebElement txt_search;
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id, 'search')]")
	@FindBy(how = How.ID, using = "search")
	public WebElement btn_search;
	
	@FindBy(how = How.ID, using = "recent-search-items")
	public WebElement div_recentSearch; 
	
	
	public SearchPage(final WebDriverUtils driver) {
		super(driver);
	}
	
	@When("^user searches keyword (.*)$")
	public void search(final String searchTxt) {
		
		typeSearchKeyword(searchTxt);
		
		driver.clickEnter(btn_search);
		driver.explicitWait();
	}

	@When("^user types keyword (.*)$")
	public void typeSearchKeyword(final String searchTxt) {
		
		driver.fillin_textbox(txt_search, searchTxt);
		driver.explicitWait();
	}
	
	@When("^user clicks search tab$")
	public void search() {
		
		driver.clickButton(txt_search);
		driver.explicitWait();
	}
	
	@Then("^recent search history should contain (.*)$")
	public void checkRecentSearch(final String recentSearch) {
		
		List<String> expected_recentSearches = recentSearch.contains(";") ? 
				Lists.newArrayList(recentSearch.split(";")): Lists.newArrayList(recentSearch);
				
		List<WebElement> list = div_recentSearch.findElements(By.xpath(".//a[@class='recent-search-item']"));
		for(WebElement elem: list) {
			String text = elem.getText();
			Assert.assertTrue(expected_recentSearches.contains(text));
		}
	}
	
	@Then("^search suggestion should contain (.*)$")
	public void checkSearchSuggestion(final String searchSuggestion) {
		
		
	}
}
