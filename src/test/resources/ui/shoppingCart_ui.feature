Feature: Shopping Cart Feature 

Scenario Outline:
Validate price, breadcrumb, category, return label, qty, product details, additional details, and addToCart button for Config. Products 

	Given user navigates to category page 
	And user set lang <lang>
	When user clicks categories <categoryPath>
	And user clicks product <product> 
	Then user add the product into shopping cart 
	 
	When user set qty in shopping cart as <qty>
	Then qty in shopping cart should be <qty>
	And price in shopping cart should be correct 
	
	When user set qty in shopping cart as 3
	Then price in shopping cart should be correct

	When user view shopping cart
	And user set promo code <promoCode>
	Then user should see a success msg <couponSuccessMsg> on shopping cart page

	When user remove promo code
	Then user should see a success msg <couponRemoveMsg> on shopping cart page	

	When user remove the product <product> from shopping cart
	Then shopping cart qty should be consistent with counter number   
	#When user set qty in shopping cart as 11
	#Then user should get an error msg (cannot handle with alert)
	Examples: 
		|lang |categoryPath           | product 				  | qty | promoCode |    couponSuccessMsg  |    couponRemoveMsg          |
		| TC  |電器產品;電腦;電腦儲存裝置;| Micromed 潔牙啫喱 30 ml   |  1   | abcd     | 您已使用電子優惠碼"abcd"| You canceled the coupon code.|
		| SC  |电器产品;电脑;电脑储存装置;| Micromed 洁牙啫喱 30 ml   |  1   | abcd     | 您已使用電子優惠碼"abcd"| You canceled the coupon code.|
		| EN  |Electronics;Computers;Storage Devices;| Micromed Pristine Tooth Polise |  1   | abcd     | You have used promo code "abcd"| You canceled the coupon code.|