Feature: REST-assured testing for create guest shopping cart API

 Scenario: Create guest shopping cart
		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/guest/en/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|data.cart_id | notHasItem | null |
