Feature: Forgot Password Functionality Feature 

Scenario Outline: Forgot Password Functionality 
	Given user navigates to forgot password page 
	And user set lang <lang>
	When user prefers to send email to <email>
	Then user should receive password reset confirmation msg <confirmMsg>
	
	Examples:
	 |lang|email         | confirmMsg                                   |
	 |TC  |sa@sa.com     | 我們已將重設密碼所需的鏈結發到您的電郵地址 sa@sa.com|
	 |SC  |sa@sa.com     | 我们已将重设密码所需的链结发到您的电邮地址 sa@sa.com|
	 |EN  |sa@sa.com     | We have sent a reset password link to your email address sa@sa.com|