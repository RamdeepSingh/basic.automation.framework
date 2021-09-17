package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Pages {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
	}

	By acceptCookie = By.xpath("//div[contains(@class,'cookies')]");
	By closeAdvertisement = By.xpath("//a[@id='advertClose']");
	By signIn = By.xpath("//a/span[@class='fantasySignInLabel']");
	

	public WebElement toAcceptCookie() {

		return getElement(acceptCookie);
	}

	public void toCloseAdvertisement() {

		if(getElement(closeAdvertisement).isDisplayed()) {
			getElement(closeAdvertisement).click();
		}
	}

	public LoginPage toGetLoginPage() {

		getElement(signIn).click();
		return getInstances(LoginPage.class);
	}
	

}
