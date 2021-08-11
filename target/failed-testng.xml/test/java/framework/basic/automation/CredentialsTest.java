package framework.basic.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import framework.base.automation.HelperTest;
import webpages.LandingPage;
import webpages.LoginPage;

public class CredentialsTest extends HelperTest {

	private static Logger log = LogManager.getLogger(HelperTest.class.getName());
	LoginPage loginPage;
	LandingPage landingPage;
	
	@BeforeMethod
	public void openApplication() throws IOException {

		log.info("try to initiate driver");
		driver = initializeDriver();
		log.info("driver initialized successfully");
		log.info("navigated to environmentURL");
		driver.get(p.getProperty("environmentURL"));
		log.info("navigated to environmentURL successfully");

	}

	@AfterMethod
	public void killBrowser() {

		log.info("try to close browser");
		driver.close();
		log.info("browser closed successfully");

	}

	@Test(dataProvider = "userCredentials")
	public void credentialsValidation(Map<String, String> map) throws IOException {

		log.info("Test: credentialsValidation started");
		String username = map.get("Username");
		String encodedPassword = map.get("Password");
		String password = decoder(encodedPassword);
		
		log.info("fetched data from userCredentials dataProvider successfully");

		landingPage = new LandingPage(driver);
		landingPage.toAcceptCookie().click();
		landingPage.toCloseAdvertisement();
		loginPage = landingPage.toGetLoginPage();
		loginPage.toEnterEmail().sendKeys(username);
		loginPage.toEnterPassword().sendKeys(password);
		loginPage.toSignIn();
		landingPage.toCloseAdvertisement();
		log.info("login tested successfully with a user");
		log.info("Test: credentialsValidation finished");

	}

	@DataProvider(name = "userCredentials")
	public Object[][] getData() throws IOException {

		File file = new File("Data.xlsx");

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		workbook.close();

		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] object = new Object[rowCount][1];
		for (int i = 0; i < rowCount; i++) {
			Map<Object, Object> map = new HashMap<Object, Object>();

			for (int j = 0; j < colCount; j++) {
				map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());

			}
			object[i][0] = map;

		}

		return object;
	}
}
