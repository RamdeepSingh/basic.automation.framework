package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Pages {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
	}

	By emailTextBox = By.xpath("//input[@type='email']");
	By passwordTextBox = By.xpath("//input[@type='password']");
	By signInButton = By.xpath("//button[@type='submit']");

	public WebElement toEnterEmail() {

		return getElement(emailTextBox);
	}

	public WebElement toEnterPassword() {

		return getElement(passwordTextBox);
	}

	public HomePage toSignIn() {

		getElement(signInButton).click();
		return getInstances(HomePage.class);
	}

	public HomePage doLogin(String username, String password) {

		toEnterEmail().sendKeys(username);
		toEnterPassword().sendKeys(password);
		return toSignIn();

	}

}
