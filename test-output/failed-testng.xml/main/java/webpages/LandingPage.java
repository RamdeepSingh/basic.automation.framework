package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[contains(@class,'cookies')]")
	private WebElement acceptCookie;
	@FindBy(xpath = "//a[@id='advertClose']")
	private WebElement closeAdvertisement;
	@FindBy(xpath = "//a/span[@class='fantasySignInLabel']")
	private WebElement signIn;

	public WebElement toAcceptCookie() {

		return acceptCookie;
	}

	public void toCloseAdvertisement() {

		if(closeAdvertisement.isDisplayed()) {
			
			closeAdvertisement.click();
		}
	}

	public LoginPage toGetLoginPage() {

		signIn.click();
		//LoginPage loginPage = new LoginPage(driver);

		return new LoginPage(driver);
	}
	

}
