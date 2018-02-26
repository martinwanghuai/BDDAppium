@FireFox
Feature: Login Functionality Feature 

Scenario Outline: Login Functionality 
	

	Given user navigates to login page 
	When user logs in using Username <username> and Password <password>
	Then login should be successful 
	
	Examples: 
		|username      |password     |
		|sc@sc.com     |12345678     |
		|sd@sd.com     |12345678     |
		