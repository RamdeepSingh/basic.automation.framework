package framework.base.automation;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import resources.Utilities;

public class HelperTest extends Utilities {

	public WebDriver driver;
	private static Logger log = LogManager.getLogger(HelperTest.class.getName());
	
	@BeforeTest
	public void openApplication() throws IOException {

		log.info("---------------------------------------------------- New Test Suite triggered --------------------------------------------------------");
		log.info("try to initiate driver");
		driver = initializeDriver();
		driver.get(p.getProperty("environmentURL"));
		log.info("driver initialized successfully");

	}

	@AfterTest
	public void killBrowser() {

		log.info("try to close browser");
		driver.close();
		log.info("browser closed successfully");

	}

}
