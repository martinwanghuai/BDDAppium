Feature: Edit Address Functionality Feature 

Scenario Outline: Edit Address Functionality 
	

	Given user navigates to login page 
	And user set lang <lang>
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	When user navigates to address page
	Then user adds address with Title <title>, First Name <firstName>, Last Name <lastName>, Phone <phone>, First Address <firstAddress>, Second Address <secondAddress>, Area <area>, Country <country>, Default Billing Address <defaultBillingAddress>, and Default Delivery Address <defaultDeliveryAddress>
	
	Examples: 
		|lang |username      |password    | title | firstName | lastName     | phone    |   firstAddress |  secondAddress | area    | country  | defaultBillingAddress | defaultDeliveryAddress |
		| EN |sa@sa.com     |12345678     | Mr    | editAddressName| editAddressNameAgain | 12345678 |  first address  |  second address | 512 | HK|  true                 | true                 |
		| TC |sa@sa.com     |12345678     | Mr    | editAddressName| editAddressNameAgain | 12345678 |  first address  |  second address | 512 | HK|  true                 | true                 |
		| SC |sa@sa.com     |12345678     | Mr    | 姓氏       | 名字          | 12345678 |  地址1         |  地址2          | 512 | HK|  true                 | true                 |
		
		