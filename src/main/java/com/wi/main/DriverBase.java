package com.wi.main;

import com.wi.main.config.DriverFactory;
import com.wi.main.listeners.ScreenshotListener;
import com.wi.main.util.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {

	protected static final Logger LOG = (Logger) LogManager.getLogger(DriverBase.class);
	private static final List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
	private static ThreadLocal<DriverFactory> driverFactoryThread;

	@BeforeSuite(alwaysRun = true)
	public static void instantiateDriverObject() throws Exception {
		ExcelUtil.setExcelFileSheet("Data");
		driverFactoryThread = ThreadLocal.withInitial(() -> {
			DriverFactory driverFactory = new DriverFactory();
			webDriverThreadPool.add(driverFactory);
			return driverFactory;
		});
	}

	public static WebDriver getDriver() throws Exception {
		return driverFactoryThread.get().getDriver();
	}

	@AfterMethod(alwaysRun = true)
	public static void clearCookies() {
		try {
			driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
		} catch (Exception ignored) {
			LOG.warn("Unable to clear cookies, driver object is not viable...");
		}
	}

	@AfterSuite(alwaysRun = true)
	public static void closeDriverObjects() {
		for (DriverFactory driverFactory : webDriverThreadPool) {
			driverFactory.quitDriver();
		}
	}
}
