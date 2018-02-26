Feature: Contact-us Functionality Feature 

Scenario Outline: Contact-us Functionality 
	
	Given user navigates to contact-us page
	And user set lang <lang>
	When user provides contact-us info like name <name>, email <email>, phone <phone>, question <question>   
	Then user should receive a contact-us confirm msg <confirmMsg> 
	
	Examples:
		|lang |name|email       | phone   | question       |confirmMsg|
		|EN   |sa  |sa@sa.com   | 88888888| Hello World    |Thanks for contacting us with your comments and questions. We'll respond to you very soon.|
		|TC   |sa  |sa@sa.com   | 88888888| Hello World    |Thanks for contacting us with your comments and questions. We'll respond to you very soon.|
		|SC   |sa  |sa@sa.com   | 88888888| 您好   |Thanks for contacting us with your comments and questions. We'll respond to you very soon.|