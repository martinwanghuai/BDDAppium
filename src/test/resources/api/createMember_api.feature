Feature: REST-assured testing for create member API

 Scenario Outline: Create member
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/en/" with the body
		"""
		{
			"customer":{
				"email":"<email>",
				"firstname":"a",
				"lastname":"a",
				"prefix": "a"
				
			},
			"password":"12345678",
			"is_subscribed":1
		}
		"""
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 30 secs
		And the JSON response should have the following
		|error[0] | equalTo | false |
		|data.customer_data.email[0]| equalTo|<email>|
		
		Examples:
		|email|
		|sah@sah.com|
		