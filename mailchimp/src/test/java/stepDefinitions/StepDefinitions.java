package stepDefinitions;

import static org.junit.Assert.assertEquals;

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

	@Before
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver(); 										// Startar Chrome
		driver.get("https:login.mailchimp.com/signup"); 					// Navigerar till mailchimp.com
		Thread.sleep(1000); 												// Vänta 1 sek
		driver.findElement(By.id("onetrust-accept-btn-handler")).click(); 	// Klickar på Accept All Cookies
	}

	@Given("I have entered {string} as a mail")
	public void i_have_entered_string_as_a_mail(String String1) throws InterruptedException {
		WebElement email = driver.findElement(By.id("email")); 				// Hittar email-rutan
		email.sendKeys("Hejhej@mail.com"); 									// Skriver in en email
		Thread.sleep(1000); 												// Vänta 1 sek
	}

	@And("I have also entered {string} as an username")
	public void i_have_also_entered_string_as_an_username(String String1) throws InterruptedException {
		WebElement user = driver.findElement(By.id("new_username"));		// Hittar användarnamnet-rutan
		user.sendKeys("pollens1"); 											// Skriver in ett användarnamn
		Thread.sleep(1000); 	   											// Vänta 1 sek
	}
	@And("I have also entered {string} as an password")
	public void i_have_also_entered_string_as_an_password(String string1) throws InterruptedException {
		WebElement password = driver.findElement(By.id("new_password")); 	// Hittar lösenords-rutan
		password.sendKeys("Pellehejsan1!"); 							 	// Skriver in ett lösenord
		Thread.sleep(1000); 											 	// Vänta 1 sek
	}
	
	@When("I press sign up")
	public void i_press_sign_up() {
		driver.findElement(By.id("create-account")).click(); 				// Klickar på sign up
	}

	@Then("I should get the correct {string} output")
	public void i_should_get_the_correct_string_output(String output) throws InterruptedException {
			
		assertEquals(output, output);
		Thread.sleep(1000); 												// Vänta i 1sek
	}
	
	@After
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(1000); 												// Vänta i 1sek
		driver.quit(); 														// stänga ner chrome
	}
}