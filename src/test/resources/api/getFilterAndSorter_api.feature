Feature: REST-assured testing for get filter and sorter API

Scenario: Get filter and sorter
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/catalog/filters-and-sorting-options/en/42/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 10 secs
		And the JSON response should have the following
		|data.filter_options.attribute_code.flatten() | containsInAnyOrder | brand, size, color|
        |data.sorting_options.attribute_code.flatten()| containsInAnyOrder | name, price|