Feature: Signup Functionality Feature 

Scenario Outline: Signup Functionality 
	
	Given user navigates to signup page 
	And user set lang <lang>
	When user signup with the following info
		|prefix|Mr|
		|firstname|jacky|
		|lastname|ming|
		|email|da02@da.com|
		|password|12345678|
		|subscribe|true|
		|rememberMe|true|
		|TNC|true|
	Then signup should be successful 
	
	Examples:
		|lang |
		|EN   |
		|TC   |
		|SC   |