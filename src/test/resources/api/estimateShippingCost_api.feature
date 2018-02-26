Feature: REST-assured testing for get cart detail API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|sc@sc.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario: Get cart detail list
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/quotes/estimate-shipping-methods/en/" with the following
		|addressId|1487|
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|error| equalTo | false|
        