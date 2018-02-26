Feature: REST-assured testing for update membership API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|tesa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario Outline: Update membership
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "PUT" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/customers/en/" with the body
		"""
		{
			"customer":{
				"email":"<email>",
				"firstname":"<firstname>",
				"lastname":"<lastname>",
				"prefix": "<prefix>",
				"dob": "<dob>",
				"gender":"<gender>"
			}
		}
		"""
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 30 secs
		And the JSON response should have the following
		|data.customer_data.firstname| equalTo|	<firstname>|
		
		Examples:
		|email      | firstname | lastname | prefix | dob       | gender|
		|testa@testa.com|  test     | test     | test   | 2015-11-11| 1     |