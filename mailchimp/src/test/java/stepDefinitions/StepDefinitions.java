package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	private WebDriver driver;
	private Random rand = new Random();
	
	@Before
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver(); 													// Startar Chrome
		driver.get("https:login.mailchimp.com/signup"); 								// Navigerar till mailchimp.com
		Thread.sleep(1000); 															// Vänta 1 sek
		driver.findElement(By.id("onetrust-accept-btn-handler")).click(); 				// Klickar på Accept All Cookies
	}

	@Given("I have entered {string} as a mail or {string} randomMail")
	public void i_have_entered_string_as_a_mail_or_string_randomMial(String mail, String random) throws InterruptedException {
		if (random.equals("0")){
			WebElement email = driver.findElement(By.id("email")); 						// Hittar email-rutan
			email.sendKeys(mail);														// Skriver in ett email
			Thread.sleep(1000); 														// Vänta 1sek
		}
		if (random.equals("1")) {
			Thread.sleep(1000); 														// Vänta 1sek
			WebElement email = driver.findElement(By.id("email")); 						// Hittar email-rutan
			email.sendKeys(rand+mail.substring(5));										// Skriver in en randomEmail
		}
	}
	@And("I have also entered {string} as an username or {string} randomUsername")
	public void i_have_also_entered_string_as_an_username_or_string_randomUsername(String username, String random) throws InterruptedException {
		if (random.equals("0")){
			WebElement user = driver.findElement(By.id("new_username")); 				// Hittar användarnamn-rutan
			user.sendKeys(username);						  							// Skriver in ett Användernamn
			Thread.sleep(1000); 							  							// Vänta 1sek
		}
		if (random.equals("1")) {
			Thread.sleep(1000); 							  							// Vänta 1sek
			WebElement user = driver.findElement(By.id("new_username")); 		  		// Hittar användarnamn-rutan
			user.sendKeys(rand.nextInt(100)+username+rand.nextInt(100));				// Skriver in ett randomAnvändernamn
		}
	}

	@And("I have also entered {string} as an password")
	public void i_have_also_entered_string_as_an_password(String string1) throws InterruptedException {
		WebElement password = driver.findElement(By.id("new_password")); 				// Hittar lösenords-rutan
		password.sendKeys("Pellehejsan1!"); 											// Skriver in ett lösenord
		Thread.sleep(1000); 															// Vänta 1 sek
	}

	@When("I press sign up")
	public void i_press_sign_up() {
		driver.findElement(By.id("create-account")).click(); 							// Klickar på sign up
	}

	@Then("I should get the correct {string} output")
	public void i_should_get_the_correct_string_output(String output) throws InterruptedException {
		String finalOutput = "";
		if (finalOutput.equals("Check your email")) {
			assertEquals(driver.findElement(By.cssSelector(".\\!margin-bottom--lv3")).getText(), output);
		} else if (finalOutput.equals("Enter a value less than 100 characters long")) {
			assertEquals(driver.findElement(By.cssSelector("invalid error")).getText(), output);
		} else if (finalOutput
				.equals("Another user with this username already exists. Maybe it's your evil twin. Spooky.")) {
			assertEquals(driver.findElement(By.cssSelector("invalid error")).getText(), output);
		} else if (finalOutput.equals("Please enter a value")) {
			assertEquals(driver.findElement(By.cssSelector(".invalid-error")).getText(), output);
		}
		Thread.sleep(1000); 															// Vänta i 1sek
	}

	@After
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(1000); 															// Vänta i 1sek
		driver.quit(); 																	// stänga ner chrome
	}
}