Feature: REST-assured testing for create sales order for member API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|

Scenario: Create sales order for member
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/quotes/new-order/" with the body
		"""
		{
  			"language":"en",
  			"paymentMethod": {                
      			"method": "pccws_stripe_mobile",
      			"additional_data": 
      				["wang"]
  			}
		}
		"""
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|data.currency_code[0] | equalTo | HKD |
