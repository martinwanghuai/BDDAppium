Feature: Synchronize Shopping Cart Feature 

Scenario Outline:
    Validate shopping cart synchronization 

	Given user navigates to login page 
	And user set lang <lang>
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	
	Given user navigates to category page 
	When user clicks categories <categoryPath>
	And user clicks product <product> 
	Then user add the product into shopping cart 
	Then user logout 
	 
	Given user navigates to category page 
	When user clicks categories <categoryPath1>
	And user clicks product <product1> 
	Then user add the product into shopping cart 
	
	Given user navigates to login page 
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	When user click shopping cart
	Then shopping cart should include <productsInShoppingCart>
	
	Examples: 
		|lang|username      |password     |categoryPath           | product 				  |categoryPath1          | product1                  | productsInShoppingCart |  
		|TC  |sa@sa.com     |12345678     |電器產品;電腦;電腦儲存裝置;| Micromed 潔牙啫喱 30 ml   |電器產品;電腦;電腦儲存裝置;| Fourflax 純天然紐西蘭亞麻籽油|Fourflax 純天然紐西蘭亞麻籽油;Micromed 潔牙啫喱 30 ml|
		|SC  |sa@sa.com     |12345678     |电器产品;电脑;电脑储存装置;| Micromed 洁牙啫喱 30 ml   |电器产品;电脑;电脑储存装置;| Fourflax 纯天然纽西兰亚麻籽油|Fourflax 纯天然纽西兰亚麻籽油;Micromed 洁牙啫喱 30 ml|
		|EN  |sa@sa.com     |12345678     |Electronics;Computers;Storage Devices;| Micromed Pristine Tooth Polise |Electronics;Computers;Storage Devices;| Fourflax Flax seed oil|Fourflax Flax seed oil;Micromed Pristine Tooth Polise|
		
		
		