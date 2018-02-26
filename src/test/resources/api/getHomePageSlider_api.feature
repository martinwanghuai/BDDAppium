Feature: REST-assured testing for get Homepage slider API

Scenario: Get customer addresses
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/homepage/slides/en"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|data.slides.type.flatten() | hasItem | CATEGORY |
		|data.slides.type.flatten() | hasItem | LINK |
