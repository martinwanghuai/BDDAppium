Feature: Terms Link Functionality Feature 

Scenario Outline: Check Terms link 
	When user navigates to Terms page 
	And user set lang <lang>
	Then user should redirect to https://uat.habbitzz.com/term-and-conditions  
	
	Examples:
		|lang |
		|EN   |
		|TC   |
		|SC   |