Feature: REST-assured testing for get categories API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|testa@testa.com|
		|password|12345678|
		|language|en|

Scenario: Get all categories
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/catalog/categories/en/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 30 secs
		And the JSON response should have the following
        |data.name.flatten().size() |  equalTo|   26 |
        |data*.findAll{it.is_active=='1'}.name.flatten().size() | equalTo|     13 |
        |data*.findAll{it.is_active=='1'}.name.flatten() | containsInAnyOrder  | Electronics, Mother\\, Baby & Kids, Beauty & Personal Care, Sports & Lifestyle, Health, Gourmet Beverage, Recommended for you, Weekly discovery, Best deals, Top sellers, New Arrivals, Today's Deal, Home and Living    |
		