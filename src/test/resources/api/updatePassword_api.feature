Feature: REST-assured testing for update customer password API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345677|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario Outline: Update customer newsletter subscription
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "PUT" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/customers/password/en/" with the following
		|old_password|<password>|
		|new_password|<new_password>|
		
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|error | equalTo | false|
		
		Examples:
		|username       |password| new_password|
		|testa@testa.com|12345678| 12345677    |
		|testa@testa.com|12345677| 12345678    |
		
Scenario Outline: New & old password should not be the same
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		
		When a user send a "PUT" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/customers/password/en/" with the following
		|old_password|<password>|
		|new_password|<new_password>|
		
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|error | equalTo | true|
		
		Examples:
		|username       |password| new_password|
		|testa@testa.com|12345678| 12345678    |
		