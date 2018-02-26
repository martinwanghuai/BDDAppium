Feature: REST-assured testing for get cart detail API

Background:
		When user dost not login
		Then a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/customers/login/" with the following
		|username|sc@sc.com|
		|password|12345678|
		|language|en|
		Then save the following
		|data.access_token[0]|saveSystemProperty|AccessToken|
		
Scenario: Get cart detail list
		Given set the following in request header 
		|Authorization|getSystemProperty|AccessToken|

		When a user send a "POST" request to "http://uat.vlvdev.com/rest/V1/mobilewebservice/quotes/billing-address/en" with the body
		"""
        {
            "address": {
                "region":  "HK",
                "region_id": "HK",
                "country_id": "HK",
                "street": [
                    "high street"
                ],
                "company": "PCCW",
                "telephone": "99999999",
                "fax": null,
                "postcode": null,
                "city": "HK",
                "firstname": "martin",
                "lastname": "wang",
                "middlename": "h",
                "prefix": null,
				"suffix": null,
                "vat_id": null,
            }
        }
		"""
		Then the status code should be 200
		And it follows schema defined by "login.json"
		And it should return within 3 secs
		And the JSON response should have the following
		|error[0]							  | equalTo | false|
		|data.billing_address.company.flatten() | notHasItem | null |
        