Feature: Mailchimp
If a monkey would try to create an account, it would probably look something like this.

@mytag
Scenario Outline: Add accounts information
Given I have entered <mail> as a mail or <random> randomMail
And I have also entered <username> as an username or <random> randomUsername
And I have also entered <password> as an password
When I press sign up 
Then I should get the correct <output> output

Examples: 
|random			|mail							|username				|password			|output										|
|	"1"				|"@mail.com"			|"Pelleplutt"		|"Qwerty1!" 	|"Check your email"				|
|	"0"				|" "							|"Pelleplutten"	|"Qwerty1!" 	|"Please enter a value"		|
|	"0"				|"Hejhe2@mail.com"|"Pelle"				|"Qwerty1!" 	|"Another user with this username already exists. Maybe it's your evil twin. Spooky."		|
| "0"				|"Hejhe2@mail.com"|"Pellehjklqazxcvbnmppqwoeirutyfhdjskalapzxmxcjgfhjkasdfghjklzxcvbnmqwertyuiooooooollksjdhfgenbdnjsjaka"	|"Qwerty1!" | "Enter a value less than 100 characters long"|
