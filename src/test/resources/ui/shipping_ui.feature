Feature: Shipping Link Functionality Feature 

Scenario Outline: Check Shipping link
 
	When user navigates to Shipping page 
	And user set lang <lang> 
	Then user should redirect to https://uat.habbitzz.com/shipping-rates-and-policies  
	
	Examples: 
		|lang|
		|en  |
		|TC  |
		|SC  |