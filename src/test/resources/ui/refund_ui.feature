Feature: Refund Link Functionality Feature 

Scenario Outline: Check Refund link 
	When user navigates to Refund page
	And user set lang <lang> 
	Then user should redirect to https://uat.habbitzz.com/orders-and-returns  
	
	Examples: 
		|lang|
		|en  |
		|TC  |
		|SC  |