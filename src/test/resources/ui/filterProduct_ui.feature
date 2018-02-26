Feature: Sort Products Functionality Feature 

Scenario Outline: Sort Products Functionality 
	

	Given user navigates to category page
	And user set lang <lang>
	When user clicks categories <category>
	And user selects filter option <filterOption> 
	Then all products should be filtered based on filter option <filterOption> 
	
	Examples: 
		|lang |category          | filterOption     |
		|TC   |電器產品;          |  Stock: Out Stock|
		|SC   |电器产品;          |  Stock: Out Stock|
		|EN   |Electronics；     |  Stock: Out Stock|