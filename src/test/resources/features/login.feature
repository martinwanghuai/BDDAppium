@Chrome
Feature: Login Functionality Feature 

Scenario Outline: Login Functionality 
	

	Given user navigates to login page 
	When user logs in using Username <username> and Password <password>
	Then login should be successful 
	
	Examples: 
		|username      |password     |
		|sa@sa.com     |12345678     |
		|sb@sb.com     |12345678     |
		