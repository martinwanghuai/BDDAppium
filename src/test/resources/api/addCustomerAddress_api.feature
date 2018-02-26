Feature: REST-assured testing for add customer address API

Background:
		Given user dost not login
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario Outline: Get customer addresses
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "PUT" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/customers/addresses/" with the body
		"""
		{
  			"language":"en",
  			"address": {                
      			"firstname": "<firstname>",
      			"lastname": "<lastname>",
      			"street": ["<street>"],                
      			"city": "<city>",
      			"region_id": <region_id>,
      			"region": "<region>",
      			"postcode": null,
      			"country_id": "<country_id>",
      			"telephone": "<telephone>",
      			"default_billing": <default_billing>,
      			"default_shipping": <default_shipping>
  			}
		}
		"""
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|data.addresses.findAll{it.region_id=='<region_id>'}.firstname.flatten() | hasItem | <firstname> |
		|data.addresses.findAll{it.region_id=='<region_id>'}.lastname.flatten() | hasItem | <lastname> |
		|data.addresses.findAll{it.region_id=='<region_id>'}.street.flatten() | hasItem | <street> |
		|data.addresses.findAll{it.region_id=='<region_id>'}.city.flatten() | hasItem | <city> |
		|data.addresses.findAll{it.region_id=='<region_id>'}.country_code.flatten() | hasItem | <country_id>|
		|data.addresses.findAll{it.region_id=='<region_id>'}.telephone.flatten() | hasItem | <telephone> |
		|data.addresses.findAll{it.default_shipping==true}.default_shipping.size() | equalTo | 1 |
		|data.addresses.findAll{it.default_shipping==true}.default_billing.size() | equalTo | 1 |
        
        Examples:
        |firstname|lastname|street      |city|region_id| region| country_id| telephone| default_billing| default_shipping|
        |martin   |martin  |High Street |HK  |513      | HK    | HK        | 99999999 |    true        | false          |