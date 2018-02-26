Feature: REST-assured testing for get product details API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario: Get product detail
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/catalog/products/en/4001"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 30 secs
		And the JSON response should have the following
        |data.product_data.weight |  greaterThanOrEqualTo | 1 |
        |data.product_data.status |  isOneOf | 1, 4 |
        |data.product_data.visibility| isOneOf | 1, 2, 3, 4|
        
        