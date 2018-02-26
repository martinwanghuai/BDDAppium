Feature: Checkout Funnel Feature 

Scenario Outline: Validate checkout funnel feature 

	Given user navigates to login page 
	And user set lang <lang>
	When user logs in using Username <username> and Password <password>
	Then login should be successful
	
	Given user navigates to category page 
	When user clicks categories <categoryPath>
	And user clicks product <product> 
	Then user add the product into shopping cart 
	And user checkout shopping cart
	
	#Then user add address in checkout funnel with First Name <firstName>, Last Name <lastName>, Phone <phone>, First Address <firstAddress>, Second Address <secondAddress>, Area <area>, and Country <country>
	And user continue with checkout funnel Step 1
	And user pay money with <payType>, <cardNo>, <expiryYear>, <expiryMonth>, <cardHolder>, <CVC>
	Then shopping cart qty should be consistent with counter number
		 
	Examples:  
		|lang |username     |password     |categoryPath           | product 				  | payType                   |    cardNo      | expiryMonth | expiryYear | cardHolder| CVC| firstName | lastName     | phone    |   firstAddress |  secondAddress | area    | country  |
		|TC   |sa@sa.com     |12345678     |電器產品;電腦;電腦儲存裝置;| Micromed 潔牙啫喱 30 ml   | Credit Card (For Website) |5123450000000008| 5          | 2021       | a         | 100 | 電腦     | 電腦          | 12345678 |  中西區1  |  中西區2 | 中西區 | 香港|
		|EN   |sa@sa.com     |12345678     |Electronics;Computers;Storage Devices;| Micromed Pristine Tooth Polise   | Credit Card (For Website) |5123450000000008| 5          | 2021       | a         | 100 | firstNameAgain| lastNameAgain | 12345678 |  first address  |  second address | Central & Western | HK|
		|SC   |sa@sa.com     |12345678     |电器产品;电脑;电脑储存装置;| Micromed 潔牙啫喱 30 ml   | Credit Card (For Website) |5123450000000008| 5          | 2021       | a         | 100 | 名字| 姓氏 | 12345678 |  街区1  |  街区2 | Central & Western | HK|