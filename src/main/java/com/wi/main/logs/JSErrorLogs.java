//package com.wi.main.logs;
//
//import com.wi.main.config.Constants;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogType;
//import org.testng.Assert;
//
//public class JSErrorLogs {
//    public static LogEntries getLogs(WebDriver driver) {
//        return driver.manage().logs().get(LogType.BROWSER);
//    }
//
//    public static Boolean isLoginErrorLog(WebDriver driver) {
//
//        Thread.sleep(Constants.TIMEOUT_SHORT);
//
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(expectedResult, actualUrl);
//
//    }
//}
