package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	public WebDriver driver;
	public WebDriverWait wait;
	public Properties p;

	public WebDriver initializeDriver() throws IOException {

		String path = System.getProperty("user.dir");
		p = new Properties();
		FileInputStream fis = new FileInputStream(path + "\\src\\main\\java\\resources\\enviornment.properties");
		p.load(fis);
		String browserName = System.getProperty("browser");
		System.out.println("browser name is: " + browserName);
		/* p.getProperty("browser") */;
		String driverPath = p.getProperty("driverPath");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + driverPath + "chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + driverPath + "geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("headlesschrome")) {
			System.setProperty("webdriver.chrome.driver", path + driverPath + "chromedriver.exe");
			ChromeOptions headlessBrowser = new ChromeOptions();
			headlessBrowser.addArguments("headless");
			driver = new ChromeDriver(headlessBrowser);
		}

		else {
			System.out.println("Invalid Browser selected. Please select 'chrome' or 'firefox' or 'headlesschrome'.");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

		return driver;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "\\reports\\failure-screenshots\\" + testCaseName
				+ ".jpg";
		FileUtils.copyFile(file, new File(screenshotPath));
		return screenshotPath;
	}

	public String decoder(String password) {

		byte[] decodedString = Base64.decodeBase64(password);
		return new String(decodedString);

	}
	
	
	public void clickOnElement(String xpath) {
		
		
	}

	public void sendKeysToElement(String xpath, String keys) {
		
		
	}
	
	
	
}
