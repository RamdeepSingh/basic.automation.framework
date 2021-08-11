package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailID;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;

	public WebElement toEnterEmail() {

		return emailID;
	}

	public WebElement toEnterPassword() {

		return password;
	}

	public HomePage toSignIn() {

		signInButton.click();
		return new HomePage(driver);
	}

}
