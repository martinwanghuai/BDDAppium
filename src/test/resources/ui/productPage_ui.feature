Feature: Product Page for Config. Products Feature 

Scenario Outline: Validate price, breadcrumb, category, return label, qty, product details, additional details, and addToCart button for Config. Products 
	
	Given user navigates to category page
	And user set lang <lang>
	When user clicks categories <categoryPath>
	And user clicks product <product>
	Then prices of config. products should change with config. options
	And breadcrumb should be <breadcrumb>
	And category should be <category>
	And it should show return label <returnLabel> and <guaranteedReturnLabel>
	And user should be able to set qty as <qty>
	And product details should not be empty
	And additional product details should not be empty
	And user should be able to add into shopping cart
		
	Examples: 
		|lang |categoryPath           | product | category   | returnLabel        | guaranteedReturnLabel   | qty |        breadcrumb             |  
		|TC   |電器產品;電腦;電腦儲存裝置;|  中文          | 電腦儲存裝置 | 7-Day Easy Return  |Product Guaranteed Return| 5   |主頁;電器產品;電腦;電腦儲存裝置;中文|
		|SC   |电器产品;电脑;电脑储存装置;|  中文          | 電腦儲存裝置 | 7-Day Easy Return  |Product Guaranteed Return| 5   |主頁;電器產品;電腦;電腦儲存裝置;中文|
		|EN   |Electronics;Computers;Storage Devices;|  cheero Grip 4 6200 | Storage Devices | 7-Day Easy Return  |Product Guaranteed Return| 5   |Home;Electronics;Computers;Storage Devices;cheero Grip 4 6200|  