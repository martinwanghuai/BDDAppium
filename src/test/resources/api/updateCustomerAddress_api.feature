Feature: REST-assured testing for update customer info. API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario Outline: Update customer addresses
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|

		When a user send a "PUT" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/customers/addresses/" with the body
		"""
		{
 			"language": "en",
  			"address":{
    				"id":<id>,
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
		|data.addresses.findAll{it.id=='<id>'}.firstname.flatten() | hasItem | <firstname> |
		|data.addresses.findAll{it.id=='<id>'}.lastname.flatten() | hasItem | <lastname> |
		|data.addresses.findAll{it.id=='<id>'}.street.flatten() | hasItem | <street> |
		|data.addresses.findAll{it.id=='<id>'}.city.flatten() | hasItem | <city> |
		|data.addresses.findAll{it.id=='<id>'}.country_code.flatten() | hasItem | <country_id> |
		|data.addresses.findAll{it.id=='<id>'}.telephone.flatten() | hasItem | <telephone> |
		|data.addresses.findAll{it.id=='<id>'}.default_shipping[0] | equalTo | <default_shipping> |
		|data.addresses.findAll{it.id=='<id>'}.default_billing[0] | equalTo | <default_billing> |
        
        Examples:
        |id  |firstname|lastname   |street|city|region_id|region|country_id|telephone|default_billing|default_shipping|
        |1541|BuyerX   |JasonX     |test  |HK  |515      |HK    |HK        |99999999 |true           |true            |       
        