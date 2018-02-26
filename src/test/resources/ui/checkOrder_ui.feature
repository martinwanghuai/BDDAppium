Feature: Check Order Functionality Feature 

Scenario Outline: Check Order Functionality 
	
	Given user navigates to login page
	And user set lang <lang> 
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	When user navigates to order page
	Then user clicks order <orderId> and checkes order status <status>, shipping method <shippingMethod>, product name <productName>, product qty <productQty>, sub-total <subTotal>, shipping <shipping>, discount <discount>, and total <total>
	
	Examples: 
		|lang |username      |password     | orderId    |  status    |   shippingMethod                   |  productName     | productQty | subTotal| shipping | discount|   total |
		|TC   |sa@sa.com     |12345678     | 2000000785 |  Processing|  Standard - Delivery within 3 days | The Herb Farm Sia天然香水|   3        | $720.00| $35.00 | -$10.00| $745.00|
		|EN   |sa@sa.com     |12345678     | 2000000785 |  Processing|  Standard - Delivery within 3 days | The Herb Farm Sia天然香水|   3        | $720.00| $35.00 | -$10.00| $745.00|
		|SC   |sa@sa.com     |12345678     | 2000000785 |  Processing|  Standard - Delivery within 3 days | The Herb Farm Sia天然香水|   3        | $720.00| $35.00 | -$10.00| $745.00|
