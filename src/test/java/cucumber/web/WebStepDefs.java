package cucumber.web;


import utility.WebDriverUtils;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import cucumber.api.java.After;

public class WebStepDefs {

	WebDriverUtils driver = null;
	

	public WebStepDefs(WebDriverUtils util) {
		
		this.driver = util;
	}
	
	
	@Then("^user should redirect to (.+)$")
	public void user_should_redirect_to_correct_page(String expectedUrl) throws Throwable {
		
		driver.explicitWait();
		Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
	}
	
	
	
	@After
	public void shutDown() throws Exception {
		if(driver != null) {
			driver.shutDown();	
		}
	}
}
