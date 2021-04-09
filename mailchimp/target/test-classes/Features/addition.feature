Feature: Mailchimp
If a monkey would try to create an account, it would probably look something like this.

@mytag
Scenario Outline: Add account information
Given I have entered <mail> as a mail 
And I have also entered <username> as an username
And I have also entered <password> as an password
When I press sign up 
Then I should get the correct <output> output

Examples: 
|mail							|username				|password				|output																																								|
|"Hejhej@mail.com"|"pollens"			|"pellehejsan1!"|"Another user with this username already exists. Maybe it's your evil twin. Spooky." |