Feature: REST-assured testing for login API

Scenario Outline: Login Functionality
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|<username>|
		|password|<password>|
		|language|en|
		
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
        |data.customer_data.email[0]						    			|     equalTo| <username> |
        |data.cart_data.total_qty[0]    								|     equalTo| 6        |
		|data.cart_data.sellers*.find{it.name=='ss'}.id[0]      		| equalTo| 27|
		|data.cart_data.sellers*.find{it.name=='Randy Yu'}.id[0]		| equalTo| 236|
		|data.cart_data.sellers*.find{it.name=='Habbitzz'}.id[0]				| equalTo| 0|
		|data.cart_data.sellers*.find{it.name=='Alice Au Shop'}.id[0]	| equalTo| 18|
		|data.cart_data.sellers*.find{it.name=='Alice Au Shop'}.id[0]	| equalTo| 18|
		|data.cart_data.sellers.items.qty.flatten()*.toInteger().sum()|equalTo|6|		
		
		Examples:
		|username       |password|
		|testa@testa.com|12345678|