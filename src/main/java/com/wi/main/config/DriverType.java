package com.wi.main.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

// Lưu ý version các trình duyệt Web phải khớp, đc support
public enum DriverType implements DriverSetup {

	CHROME {
		public WebDriver getWebDriver() {
			System.setProperty("webdriver.chrome.driver", getOperatingSystemPath(CHROME));
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}
	},
	FIREFOX {
		public WebDriver getWebDriver() {
			System.setProperty("webdriver.gecko.driver", getOperatingSystemPath(FIREFOX));
			WebDriver driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}
	};

	public static final DriverType BROWSER_DEFAULT = CHROME;

	private static String getOperatingSystemPath(DriverType type) {
		String driverName = "";
		if (type == FIREFOX) {
			driverName = "geckodriver";
		} else {
			driverName = "chromedriver";
		}

		Path resourceDirectory = Paths.get("src", "main", "resources", "webDrivers");
		String webDriverPath = resourceDirectory.toFile().getAbsolutePath();
		String result = "";
		String currentOperatingSystemName = System.getProperties().getProperty("os.name");
		if (currentOperatingSystemName.toLowerCase().contains("mac")) {
			result = webDriverPath + File.separator + "macos" + File.separator + driverName;
		} else if (currentOperatingSystemName.toLowerCase().contains("linux")) {
			result = webDriverPath + File.separator + "linux" + File.separator + driverName;
		} else {
			// Window
			result = webDriverPath + File.separator + "window" + File.separator + driverName + ".exe";
		}
		return result;
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
