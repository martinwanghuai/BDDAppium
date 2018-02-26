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
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/quotes/validation/en/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|error[0]								  | equalTo | false|
		|data.addresses.default_shipping.flatten() | hasItem | true |
		|data.addresses.default_billing.flatten()  | hasItem | true |
        