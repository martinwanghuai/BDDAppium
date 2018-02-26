Feature: Recent Search Functionality Feature 

Scenario Outline: Recent Search Functionality 
	

	Given user navigates to search page
	And user set lang <lang>
	When user searches keyword <searchKey> 
	When user clicks search tab
	Then recent search history should contain <recentSearch> 
	
	Examples: 
		|lang|searchKey  |recentSearch     |
		|en  |dyson      |  dyson |
		|TC  |dyson      |  dyson |
		|SC  |dyson      |  dyson |
