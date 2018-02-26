Feature: Delete Address Functionality Feature 

Scenario Outline: Delete Address Functionality 
	

	Given user navigates to login page
	And user set lang <lang> 
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	When user navigates to address page
	Then user deletes address <addressIndex>
	
	Examples: 
		|lang |username      |password     | addressIndex |
		|EN   |sa@sa.com     |12345678     | 2           |
		|TC   |sa@sa.com     |12345678     | 2           |
		|SC   |sa@sa.com     |12345678     | 2           |
