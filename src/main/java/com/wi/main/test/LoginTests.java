package com.wi.main.test;

import com.wi.main.listeners.TestListener;
import com.wi.main.util.ExcelUtil;
import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.wi.main.util.extentreports.ExtentTestManager.startTest;

@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Login Tests")
public class LoginTests extends BaseTest {
	@SneakyThrows
	@BeforeTest
	public void setupTestData() {
		ExcelUtil.setExcelFileSheet("Data");
	}

	@Test(priority = 1, description = "Success Login Scenario")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Đăng nhập thành công")
	@Story("Đúng username password")
	public void loginSuccess(Method method) {
		startTest(method.getName(), "Đăng nhập thành công");
		loginPage
				.goToLoginPage()
				.loginWithExcelData(ExcelUtil.getRowData(1))
				.verifyLoginSuccess(ExcelUtil.getCellData(1, 9))
				.saveTestResults(1, 3);
	}

	@Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Đăng nhập thất bại, sai username, password.")
	@Story("Sai username, password")
	public void invalidUsernamePassword(Method method) {
		startTest(method.getName(), "Đăng nhập thất bại, sai username, password.");
		loginPage
				.goToLoginPage()
				.loginWithExcelData(ExcelUtil.getRowData(2))
				.verifyErrorMessage(ExcelUtil.getCellData(2, 9))
				.saveTestResults(2, 3);
	}
}