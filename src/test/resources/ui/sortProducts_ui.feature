Feature: Sort Products Functionality Feature 

Scenario Outline: Sort Products Functionality 
	

	Given user navigates to category page
	And user set lang <lang>
	When user clicks categories <category>
	And user selects sort option <sortOption> 
	Then all products should be sorted based on sort option <sortOption> 
	
	Examples: 
		|lang |category     | sortOption     |
		|TC   |電器產品;Gadgets;App Toys;| 價錢 |
		|SC   |电器产品;Gadgets;App Toys;| 价格 |
		|EN   |Electronics;Gadgets;App Toys;| Price |