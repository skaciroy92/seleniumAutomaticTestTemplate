package com.wi.main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.wi.main.DriverBase.getDriver;

public class BasePage {
	public WebDriver driver;

	public BasePage(WebDriver driver) throws Exception {
		this.driver = getDriver();
	}

	public void click(By by) {
		findElement(by).click();
	}

	public void writeText(By by, String text) {
		findElement(by).sendKeys(text);
	}

	public String readText(By by) {
		return findElement(by).getText();
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}
}