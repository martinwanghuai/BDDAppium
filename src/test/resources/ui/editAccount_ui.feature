Feature: Edit Account Functionality Feature 

Scenario Outline: Edit Account Functionality 
	

	Given user navigates to login page 
	And user set lang <lang>
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	When user navigates to account page
	Then user updates Title <title>, First Name <firstName>, and Last Name <lastName>
	
	Examples: 
		|lang |username      |password     | title | firstName | lastName |
		|EN   |sa@sa.com     |12345678     | Mr | editFirstName| editLastName |
		|TC   |sa@sa.com     |12345678     | Mr | editFirstName| editLastName |
		|SC   |sa@sa.com     |12345678     | Mr | 姓氏| 名字 | 