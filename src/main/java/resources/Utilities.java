package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	public WebDriver drive;
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
			drive = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path + driverPath + "geckodriver.exe");
			drive = new FirefoxDriver();
			
		} else if (browserName.equalsIgnoreCase("headlesschrome")) {
			System.setProperty("webdriver.chrome.driver", path + driverPath + "chromedriver.exe");
			ChromeOptions headlessBrowser = new ChromeOptions();
			headlessBrowser.addArguments("headless");
			drive = new ChromeDriver(headlessBrowser);
		}

		else {
			System.out.println("Invalid Browser selected. Please select 'chrome' or 'firefox' or 'headlesschrome'.");
		}

		drive.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(drive, 15);
		drive.manage().window().maximize();

		return drive;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "\\reports\\failure-screenshots\\" + testCaseName
				+ ".jpg";
		FileUtils.copyFile(file, new File(screenshotPath));
		return screenshotPath;
	}

}
