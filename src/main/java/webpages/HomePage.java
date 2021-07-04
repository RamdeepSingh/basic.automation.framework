package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[@class='fantasyUsername']")
	private WebElement userInfo;
	@FindBy(xpath = "//a[contains(@class,'racism')]")
	private WebElement noRacismMessage;
	@FindBy(xpath = "//span[@class='fantasyUsername']")
	private WebElement fantasyUsername;

	public WebElement toGetUserInfo() {

		return userInfo;
	}

	public WebElement noRacismTitle() {

		return noRacismMessage;
	}

	public WebElement toFetchFantasyUsername() {

		return fantasyUsername;
	}

}
