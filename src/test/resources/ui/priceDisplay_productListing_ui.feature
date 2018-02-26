Feature: Display Price in Product Listing Feature 

Scenario Outline: Display Price in Product Listing 
	
	Given user navigates to category page
	And user set lang <lang>
	When user clicks categories <category>
	Then prices of products in product listing page should have 2 decimal places 
	
	Examples: 
		|lang |category          |
		|TC   |電器產品;          |
		|SC   |电器产品;          |
		|EN   |Electronics；     |