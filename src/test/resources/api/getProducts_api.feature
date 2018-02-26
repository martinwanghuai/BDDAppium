#an example for the following:
#validate response value not contain some values
Feature: REST-assured testing for get product list for a category API
Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario: Get products under a category
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/catalog/products/" with the following
		#for category "Bottles & Mugs"
		 
		|language|en|
		|subCategoryId|125| 
		|sortField|price|
		|sortOrder|DESC|
		|limit|100|
		|lastProductId|null|
		|filters.color|null|
		|filters.brand|null|
		
		Then the status code should be 200
		
		And it follows schema defined by "login.json"
		And it should return within 30 secs
		And the JSON response should have the following
		|data.items.is_active.flatten()|notHasItem|false|
		|data.items.is_active.flatten()|hasItem|true|
		
		