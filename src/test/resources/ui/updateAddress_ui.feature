Feature: Update Address Functionality Feature 

Scenario Outline: Update Address Functionality 
	

	Given user navigates to login page 
	And user set lang <lang>
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	When user navigates to address page
	Then user updates first address with Title <title>, First Name <firstName>, Last Name <lastName>, Phone <phone>, First Address <firstAddress>, Second Address <secondAddress>, Area <area>, Country <country>, Default Billing Address <defaultBillingAddress>, and Default Delivery Address <defaultDeliveryAddress>
	
	Examples: 
		|lang |username      |password     | title | firstName | lastName     | phone    |   firstAddress |  secondAddress | area    | country  | defaultBillingAddress | defaultDeliveryAddress |
		|TC   |sa@sa.com     |12345678     | Mr | updateFirstName| updateLastName | 12345678 |  updated first address  |  updated second address | 512 | HK|  true                 | true                 |
		|SC   |sa@sa.com     |12345678     | Mr | updateFirstName| updateLastName | 12345678 |  updated first address  |  updated second address | 512 | HK|  true                 | true                 |
		|EN   |sa@sa.com     |12345678     | Mr | updateFirstName| updateLastName | 12345678 |  updated first address  |  updated second address | 512 | HK|  true                 | true                 |
