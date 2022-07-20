package com.aspiration.config;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	public static ThreadLocal<WebDriver> driverManager = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return driverManager.get();
	}

	public static void setWebDriver(WebDriver driver) {
		driverManager.set(driver);
	}

	public static void removeDriverManager(){
		driverManager.remove();
	}
}
