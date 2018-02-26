Feature: Login Functionality Feature 

Scenario Outline: Login Functionality 
	

	Given user navigates to login page 
	And user set lang <lang>
	When user logs in using Username <username> and Password <password>
	Then login should be successful 
	
	Examples: 
		|lang|username      |password     |
		|EN  |sa@sa.com     |12345678     |
		|TC  |sa@sa.com     |12345678     |
		|SC  |sa@sa.com     |12345678     |