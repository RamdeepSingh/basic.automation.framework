package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Pages {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		super(driver);
		this.driver = driver;
	}

	By userInfo = By.xpath("//span[@class='fantasyUsername']");
	By noRacismMessage = By.xpath("//a[contains(@class,'racism')]");
	By fantasyUsername = By.xpath("//span[@class='fantasyUsername']");

	public WebElement toGetUserInfo() {

		return getElement(userInfo);
	}

	public WebElement noRacismTitle() {

		return getElement(noRacismMessage);
	}

	public WebElement toFetchFantasyUsername() {

		return getElement(fantasyUsername);
	}

}
