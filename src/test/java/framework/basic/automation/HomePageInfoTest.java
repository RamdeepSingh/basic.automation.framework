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
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.base.automation.HelperTest;
import webpages.HomePage;
import webpages.LandingPage;
import webpages.LoginPage;

public class HomePageInfoTest extends HelperTest {

	HomePage homepage;
	LandingPage landingPage;
	LoginPage loginPage;

	private static Logger log = LogManager.getLogger(HelperTest.class.getName());

	@Test(dataProvider = "userInfo", enabled = true)
	public void loginToApplication(Map<String, String> map) {

		log.info("Test: loginToApplication started");
		String username = map.get("Username");
		String password = map.get("Password");

		landingPage = new LandingPage(driver);
		landingPage.toAcceptCookie().click();
		landingPage.toCloseAdvertisement();
		loginPage = landingPage.toGetLoginPage();
		loginPage.toEnterEmail().sendKeys(username);
		loginPage.toEnterPassword().sendKeys(password);
		homepage = loginPage.toSignIn();
		landingPage.toCloseAdvertisement();
		log.info("Test: loginToApplication finished");

	}

	@Test(dataProvider = "userInfo", dependsOnMethods = "loginToApplication")
	public void validateUserName(Map<String, String> map) {

		try {
			log.info("Test: validateUserName started");
			String expectedUser = map.get("User");
			Assert.assertEquals(homepage.toGetUserInfo().getText(), expectedUser);
			log.info("Test: validateUserName finished");
		} catch (Exception e) {
			log.info("Test: validateUserName FAILED");

		}
	}

	@Test(dependsOnMethods = "validateUserName")
	public void noRacism() {

		try {
			log.info("Test: noRacism started");
			Assert.assertTrue(homepage.noRacismTitle().isDisplayed());
			Assert.assertTrue(homepage.noRacismTitle().getText().equalsIgnoreCase("No Room for Racis"));
			log.info("Test: noRacism finished");
		} catch (Exception e) {
			log.info("Test: noRacism FAILED");
		}
	}

	@DataProvider(name = "userInfo")

	public Object[][] getData() throws IOException {

		File file = new File("Data.xlsx");

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		workbook.close();

		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] object = new Object[1][1];
		for (int i = 0; i < rowCount - 1; i++) {
			Map<Object, Object> map = new HashMap<Object, Object>();

			for (int j = 0; j < colCount; j++) {

				map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());

			}
			object[0][0] = map;

		}

		return object;
	}

}
