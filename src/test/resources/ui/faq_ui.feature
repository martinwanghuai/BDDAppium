Feature: FAQ Link Functionality Feature 

Scenario Outline: Check FAQ link 
	When user navigates to FAQ page 
	And user set lang <lang>
	Then user should redirect to https://uat.habbitzz.com/faq
	
	Examples:
		|lang |
		|EN   |
		|TC   |
		|SC   |