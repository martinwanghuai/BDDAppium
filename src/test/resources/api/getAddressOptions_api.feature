Feature: REST-assured testing for get address options API

Scenario: Get all address options
		When a user send a "GET" request to "http://uat.vlvdev.com/rest/V2/mobilewebservice/directory/countries/HK/areas/en/"
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 30 secs
		And the JSON response should have the following
        |data.area_options.flatten().size() |  equalTo| 18 |
