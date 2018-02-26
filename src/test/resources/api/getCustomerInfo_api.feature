Feature: REST-assured testing for get customer info. API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|

Scenario: Get customer info
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/info/en/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
        |data.customer_data.email[0] | equalTo | testa@testa.com|
        |data.customer_data.firstname[0] | equalTo | testa|
        |data.customer_data.lastname[0] | equalTo | testa|
        