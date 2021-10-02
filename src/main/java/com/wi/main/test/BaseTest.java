package com.wi.main.test;

import com.wi.main.pages.LoginPage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	protected WebDriver driver;

	protected LoginPage loginPage;

	public WebDriver getDriver() {
		return driver;
	}

	@SneakyThrows
	@BeforeClass
	public void classLevelSetup() {
		driver = com.wi.main.DriverBase.getDriver();
	}

	@SneakyThrows
	@BeforeMethod
	public void methodLevelSetup() {
		loginPage = new LoginPage(driver);
	}

	@AfterClass
	public void teardown() {
		if (null != driver) {
			driver.quit();
		}
	}
}