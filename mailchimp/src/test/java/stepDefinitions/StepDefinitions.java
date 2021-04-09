package stepDefinitions;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

public class StepDefinitions {

	private WebDriver driver;
	private WebElement email,user,pass;
	private Random rand = new Random();
	
	@Before
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver(); 									    
		driver.get("https:login.mailchimp.com/signup"); 				  	
		Thread.sleep(1000); 												// Behövdes för att kunna ta bort cookies och få gröna junittest (funkade inte med impicitlywait)											  	
		driver.findElement(By.id("onetrust-accept-btn-handler")).click(); 	// Klickar på "Accept All Cookies"
	}

	@Given("I have entered {string} as a mail or {string} randomMail")
	public void i_have_entered_string_as_a_mail(String mail, String random) throws InterruptedException {
	if (random.equals("0")){
		email = driver.findElement(By.id("email")); 						
		email.sendKeys(mail);												
		 												
	}
	if (random.equals("1")) {
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);	// Vänta för random mail
		email = driver.findElement(By.id("email")); 						// Hittar email-rutan
		email.sendKeys(rand+mail.substring(5));								// Skriver in en randomEmail
	}
}
	
	@And("I have also entered {string} as an username or {string} randomUsername")
	public void i_have_also_entered_string_as_an_username_or_string_randomUsername(String username, String random) throws InterruptedException {
		if (random.equals("0")){
			user = driver.findElement(By.id("new_username")); 				
			user.sendKeys(username);						  				 							  				
		}
		if (random.equals("1")) {
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS); 	// Vänta för random username
			user = driver.findElement(By.id("new_username")); 		  			// Hittar användarnamn-rutan
			user.sendKeys(rand.nextInt(100)+username+rand.nextInt(100));		// Skriver in ett randomAnvändernamn
		}
	}
	
	@And("I have also entered {string} as an password")
	public void i_have_also_entered_string_as_an_password(String password) throws InterruptedException {
		pass = driver.findElement(By.id("new_password")); 					
		pass.sendKeys(password); 						  					
	}
	public static void click(WebDriver driver, By by) {							//Waitmetoden
		(new WebDriverWait(driver,1000)).until(ExpectedConditions.
		elementToBeClickable(by));
		driver.findElement(by).click();
		}

	@When("I press sign up")
	public void i_press_sign_up() {
		driver.findElement(By.id("create-account")).click(); 				
	}
	
	@Then("I should get the correct {string} output")
	public void i_should_get_the_correct_string_output(String output) throws InterruptedException {
		String finalOutput = "";
		if (finalOutput.equals("Check your email")) {
			 assertEquals(driver.findElement(By.cssSelector(".\\!margin-bottom--lv3")).getText(), output);
		}
		else if (finalOutput.equals("Enter a value less than 100 characters long")) {
			assertEquals(driver.findElement(By.cssSelector("invalid error")).getText(), output);
		}
		else if (finalOutput.equals("Another user with this username already exists. Maybe it's your evil twin. Spooky.")) {
			assertEquals(driver.findElement(By.cssSelector("invalid error")).getText(), output);
		}
		else if (finalOutput.equals("Please enter a value")) {
			assertEquals(driver.findElement(By.cssSelector(".invalid-error")).getText(), output);
		}									
	}

	@After
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(3000); 												// Behövdes för att kunna se hur testet gick som jag ville												
		driver.quit(); 														
	}
}