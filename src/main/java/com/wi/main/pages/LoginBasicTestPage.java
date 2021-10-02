package com.wi.main.pages;

import com.wi.main.config.Constants;
import com.wi.main.listeners.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.wi.main.DriverBase.getDriver;

@Listeners(ScreenshotListener.class)
public class LoginBasicTestPage {

//	@Test
	public void loginSuccess() throws Exception {
		String expectedResult = "https://dev.broadcast.wiinvent.tv/channels";
		WebDriver driver = getDriver();
		driver.get("https://dev.broadcast.wiinvent.tv");
		WebElement username = driver.findElement(By.id("exampleInputEmail"));
		WebElement password = driver.findElement(By.id("exampleInputPassword"));
		WebElement login = driver.findElement(By.className("btn-lg"));
		username.sendKeys("Testgiaodien@wiinvent.tv");
		password.sendKeys("Test@321");
		login.click();
		Thread.sleep(Constants.TIMEOUT_SHORT);

		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedResult, actualUrl);
	}

//	@Test
	public void loginFail() throws Exception {
		String expectedResult = "https://dev.broadcast.wiinvent.tv/login";
		WebDriver driver = getDriver();
		driver.get("https://dev.broadcast.wiinvent.tv");
		WebElement username = driver.findElement(By.id("exampleInputEmail"));
		WebElement password = driver.findElement(By.id("exampleInputPassword"));
		WebElement login = driver.findElement(By.className("btn-lg"));
		username.sendKeys("Testgiaodien@wiinvent.tv1");
		password.sendKeys("Test@321");
		login.click();
		Thread.sleep(Constants.TIMEOUT_SHORT);

		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedResult, actualUrl);
	}


}
