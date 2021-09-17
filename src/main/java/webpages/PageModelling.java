package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class PageModelling {

	WebDriver driver;
	WebDriverWait wait;

	public PageModelling(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);

	}

		// hidden items
		public abstract String getPageTitle();

		public abstract String getPageHeaderText(By locator);

		public abstract WebElement getElement(By locator);

		public abstract void waitForElementPresent(By locator);

		public abstract void waitForPageToLoad(String title);

		public <TPage extends Pages> TPage getInstances(Class<TPage> pageClass) {

			try {
				return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}
	
}
