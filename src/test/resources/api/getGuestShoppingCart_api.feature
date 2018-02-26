Feature: REST-assured testing for get guest shopping cart API

 Scenario: Get guest shopping cart
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/guest/quotes/carts/en/c4f827b96cb75e2b4de3ae671b570875"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|data.cart_data.totals.currency_code.flatten() | hasItem | HKD |