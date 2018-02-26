Feature: Privacy Link Functionality Feature 

Scenario Outline: Check Privacy link 
	When user navigates to Privacy page
	And user set lang <lang>
	Then user should redirect to https://uat.habbitzz.com/privacy-policy
	
	Examples:
		|lang |
		|EN   |
		|TC   |
		|SC   |