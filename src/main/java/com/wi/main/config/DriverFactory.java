package com.wi.main.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
	private static final Logger LOG = (Logger) LogManager.getLogger(DriverFactory.class);

	private WebDriver driver;
	private final DriverType selectedDriverType;

	public DriverFactory() {
		selectedDriverType = DriverType.BROWSER_DEFAULT;
	}

	public WebDriver getDriver() throws Exception {
		if (null == driver) {
			instantiateWebDriver(selectedDriverType);
		}

		return driver;
	}

	public WebDriver getStoredDriver() {
		return driver;
	}

	public void quitDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}

	private void instantiateWebDriver(DriverType driverType) throws Exception {
		driver = DriverType.BROWSER_DEFAULT.getWebDriver();
	}

}

