package com.wi.main.pages;

import com.wi.main.config.Constants;
import com.wi.main.util.ExcelUtil;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertEquals;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) throws Exception {
		super(driver);
	}

	By usernameBy = By.id("exampleInputEmail");
	By passwordBy = By.id("exampleInputPassword");
	By btnLoginBy = By.className("btn-lg");
	By errorMessageBy = By.xpath("//form[@class=\"pt-3\"]/span");

	@Step("Go to Login Page Step...")
	public LoginPage goToLoginPage() {
		driver.get("https://dev.broadcast.wiinvent.tv");
		return this;
	}

	@Step
	public LoginPage login(String username, String password) {
		writeText(usernameBy, username);
		writeText(passwordBy, password);
		click(btnLoginBy);
		return this;
	}

	@Step
	public LoginPage loginWithExcelData(XSSFRow row) {
		writeText(usernameBy, row.getCell(4).toString());
		writeText(passwordBy, row.getCell(5).toString());
		click(btnLoginBy);
		return this;
	}

	@SneakyThrows
	@Step
	public LoginPage verifyLoginSuccess(String expectedText) {
		Thread.sleep(3000);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedText, actualUrl);
		return this;
	}

	@SneakyThrows
	@Step
	public LoginPage verifyErrorMessage(String expectedText) {
		Thread.sleep(Constants.TIMEOUT_SHORT);
		assertEquals(readText(errorMessageBy), expectedText);
		return this;
	}

	@Step
	public LoginPage saveTestResults(int row, int column) {
		ExcelUtil.rowNumber = row;
		ExcelUtil.columnNumber = column;
		return this;
	}
}
