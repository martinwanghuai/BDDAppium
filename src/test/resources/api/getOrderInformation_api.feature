Feature: REST-assured testing for get customer order information API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|sc@sc.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario: Get customer order information
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/customers/orders/600/en/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|data.order_details.total_info.grand_total[0] | equalTo | 115 |
		|data.order_details.total_info.sub_total[0] | equalTo | 80 |
		|data.order_details.total_info.shipping_cost[0] | equalTo | 35 |
		|data.order_details.total_info.discount_amount[0] | equalTo | 0 |
        