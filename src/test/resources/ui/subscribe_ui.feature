Feature: Subscribe Functionality Feature 

Scenario Outline: Subscribe Functionality 
	
	Given user navigates to home page
	And user set lang <lang>
	When user provides subscribe email <email>  
	Then user should receive a confirm email <confirmEmail> 
	
	Examples:
		|lang |email       | confirmEmail|
		|TC   |sa@sa.com   |  sa@sa.com|
		|SC   |sa@sa.com   |  sa@sa.com|
		|EN   |sa@sa.com   |  sa@sa.com|