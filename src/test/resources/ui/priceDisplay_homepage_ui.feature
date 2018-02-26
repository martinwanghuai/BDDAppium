Feature: Display Price in Homepage Functionality Feature 

Scenario Outline: Display Price in Homepage Functionality 
	
	When user navigates to home page 
	And user set lang <lang>
	Then prices of products in homepage should have 2 decimal places 

	Examples:
		|lang |
		|EN   |
		|TC   |
		|SC   |